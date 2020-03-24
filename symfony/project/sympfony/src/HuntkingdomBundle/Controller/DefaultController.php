<?php

namespace HuntkingdomBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('HuntkingdomBundle:Default:index.html.twig');
    }
}
