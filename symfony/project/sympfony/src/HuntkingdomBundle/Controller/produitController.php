<?php

namespace HuntkingdomBundle\Controller;
use Knp\Component\Pager\Paginator;
use HuntkingdomBundle\Entity\Product;
use HuntkingdomBundle\Form\ProductType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Routing\Annotation\Route;



class produitController extends Controller
{
    public function ajouterProduitAction(Request $request)
    {
        $Product= new Product();
        $form=$this->createForm(ProductType::class,$Product);
        $form->handleRequest($request);
        if ($form->isSubmitted())
        {
            /**
             *  @var UploadedFile $file
             */
            $file=$Product->getImage();
            $fileName= md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter( 'image_directory'),$fileName);
            $Product->setImage($fileName);
            $em=$this->getDoctrine()->getManager();
            $em->persist($Product);
            $em->flush();

          $this->addFlash('info','ajout avec succées');
        }
        return $this->render('@Huntkingdom/commerce/ajouterProduit.html.twig',array('form'=>$form->createView()));
    }

    public  function afficherProduitAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $dql = "SELECT a from HuntkingdomBundle:Product a";
        $query=$em->createQuery($dql);
       // $posts=$em->getRepository("HuntkingdomBundle:Product")->findAll();
        /**
         * @var $paginator  Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result=$paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',1)
        );


        return $this->render("@Huntkingdom/commerce/afficherProduit.html.twig",array( 'posts' => $result));


    }

    public function supprimerProduitAction($id)
    {


        $em = $this->getDoctrine()->getManager();
        $Product = $em->getRepository("HuntkingdomBundle:Product")->find($id);
        $em->remove($Product);
        $em->flush();

        return $this->redirectToRoute("commerce_afficherProduit");
    }
    public function updateProduitAction(Request $request,$id)
    {
        $c= $this->getDoctrine()->getManager();
        $Product = $c->getRepository("HuntkingdomBundle:Product")->find($id);
        $form=$this->createForm(ProductType::class,$Product);
        $form->handleRequest($request);
        if ($form->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($Product);
            $em->flush();
            return $this->redirectToRoute("commerce_afficherProduit");
        }
        return $this->render('@Huntkingdom/commerce/ajouterProduit.html.twig',array('form'=>$form->createView()));
    }
}
