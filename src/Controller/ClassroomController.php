<?php

namespace App\Controller;

use App\Entity\Classroom;
use App\Repository\ClassroomRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Form\ClassroomType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ClassroomController extends AbstractController
{
    /**
     * @Route("/classroom", name="classroom")
     */
    public function index(): Response
    {
        return $this->render('classroom/index.html.twig', [
            'controller_name' => 'ClassroomController',
        ]);
    }

    /**
     *  @param ClubRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/AfficheClass",name="AfficheClass")
     */
    public function Affiche(ClassroomRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Classroom::class);
        $classroom=$repository->findAll();
        return $this->render('classroom/Affiche.html.twig',
            ['classroom'=>$classroom]);
        
    }

    /**
     * @Route("/Supp/{id}",name="d")
     */
    function Delete($id, ClassroomRepository $repository){
        $classroom=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($classroom);
        $em->flush();
        return $this->redirectToRoute('AfficheClass');

    }

        /**
         * @param Request $request
         * @return \Symfony\Component\HttpFoundation\Response
         * @Route("classroom/Add")
         */

    function Add(Request $request){
        $classroom= new Classroom();
        $form=$this->createForm(ClassroomType::class,$classroom);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($classroom);
            $em->flush();
            return $this->redirectToRoute('AfficheClass');
        }
        return $this->render('classroom/Add.html.twig',[
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("classroom/Update/{id}",name="update")
     */
    function Update(ClassroomRepository $repository,$id, Request $request){
        $classroom=$repository->find($id);
        $form=$this->createForm(ClassroomType::class,$classroom);
        $form->add('Update' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheClass');
        }
        return $this->render('classroom/Update.html.twig',
        [
            'f'=>$form->createView()
        ]);
    }
}
