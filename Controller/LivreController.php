<?php

namespace App\Controller;

use App\Entity\Livre;
use App\Repository\LivreRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Form\LivreType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LivreController extends AbstractController
{
    /**
     * @Route("/livre", name="livre")
     */
    public function index(): Response
    {
        return $this->render('livre/indexOld.html.twig', [
            'controller_name' => 'LivreController',
        ]);
    }

    /**
     *  @param LivreRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("livre/Affiche",name="Affiche")
     */
    public function Affiche(LivreRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Livre::class);
        $livre_audio=$repository->findAll();
        return $this->render('livre/Affiche.html.twig',
            ['livre'=>$livre_audio]);
        
    }

    /**
     * @Route("/Supp/{id}",name="d")
     */
    function Delete($id, LivreRepository $repository){
        $livre_audio=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($livre_audio);
        $em->flush();
        return $this->redirectToRoute('Affiche');

    }

        /**
         * @param Request $request
         * @return \Symfony\Component\HttpFoundation\Response
         * @Route("livre/Add", name="add")
         */

    function Add(Request $request){
        $livre_audio= new Livre();
        $form=$this->createForm(LivreType::class , $livre_audio);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($livre_audio);
            $em->flush();
            return $this->redirectToRoute('Affiche');
        }
        return $this->render('livre/Add.html.twig',[
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("livre/Update/{id}",name="update")
     */
    function Update(LivreRepository $repository, $id, Request $request){
        $livre_audio=$repository->find($id);
        $form=$this->createForm(LivreType::class,$livre_audio);
        $form->add('Modifier' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Affiche');
        }
        return $this->render('livre/Update.html.twig',
        [
            'f'=>$form->createView()
        ]);
    }
}
