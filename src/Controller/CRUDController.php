<?php

namespace App\Controller;

use App\Entity\Jeu;
use App\Repository\JeuRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Form\CRUDType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CRUDController extends AbstractController
{
    /**
     * @Route("/crudjeu", name="crud")
     */
    public function index(): Response
    {
        return $this->render('crud/index.html.twig', [
            'controller_name' => 'CRUDController',
        ]);
    }

    /**
     *  @param JeuRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/AfficheJeu",name="AfficheJeu")
     */
    public function Affiche(JeuRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Jeu::class);
        $jeu=$repository->findAll();
        return $this->render('crud/Affichej.html.twig',
            ['jeu'=>$jeu]);
        
    }

    /**
     * @Route("/SuppJeu/{id}",name="dj")
     */
    function Delete($id, JeuRepository $repository){
        $jeu=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($jeu);
        $em->flush();
        return $this->redirectToRoute('AfficheJeu');

    }

        /**
         * @param Request $request
         * @return \Symfony\Component\HttpFoundation\Response
         * @Route("crud/AddJeu",name="AddJeu")
         */

    function Add(Request $request){
        $jeu= new Jeu();
        $form=$this->createForm(CRUDType::class,$jeu);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($jeu);
            $em->flush();
            return $this->redirectToRoute('AfficheJeu');
        }
        return $this->render('crud/AddJeu.html.twig',[
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("crud/UpdateJeu/{id}",name="updatejeu")
     */
    function Update(JeuRepository $repository,$id, Request $request){
        $jeu=$repository->find($id);
        $form=$this->createForm(CRUDType::class,$jeu);
        $form->add('UpdateJeu' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheJeu');
        }
        return $this->render('crud/UpdateJeu.html.twig',
        [
            'f'=>$form->createView()
        ]);
    }

}
