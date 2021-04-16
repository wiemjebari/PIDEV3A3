<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Form\UserType;
use App\Form\Usersearch;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class UserController extends AbstractController
{
    /**
     * @Route("/Main", name="Main")
     */
    public function mainpage(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'UserController',
        ]);
    }

    /**
     * @Route("/Mainfront", name="Mainfront")
     */
    public function mainf(): Response
    {
        return $this->render('basef.html.twig', [
            'controller_name' => 'UserController',
        ]);
    }

    /**
     * @Route("/user", name="user")
     */
    public function index(): Response
    {
        return $this->render('user/index.html.twig', [
            'controller_name' => 'UserController',
        ]);
    }

    /**
     *  @param UserRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/AfficheUser",name="AfficheUser")
     */
    public function Affiche(UserRepository $repository){
        $repo=$this->getDoctrine()->getRepository(User::class);
        $user=$repository->findAll();
        return $this->render('user/Afficheu.html.twig',
            ['user'=>$user]);
        
    }

    /**
     * @Route("/SuppUser/{id}",name="du")
     */
    function Delete($id, UserRepository $repository){
        $user=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('AfficheUser');

    }

        /**
         * @param Request $request
         * @return \Symfony\Component\HttpFoundation\Response
         * @Route("user/AddUser",name="AddUser")
         */

    function Add(Request $request){
        $user= new User();
        $form=$this->createForm(UserType::class,$user);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('AfficheUser');
        }
        return $this->render('user/AddUser.html.twig',[
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("user/UpdateUser/{id}",name="updateUser")
     */
    function Update(UserRepository $repository,$id, Request $request){
        $user=$repository->find($id);
        $form=$this->createForm(UserType::class,$user);
        $form->add('UpdateUser' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheUser');
        }
        return $this->render('user/UpdateUser.html.twig',
        [
            'u'=>$form->createView()
        ]);
    }

    /**
     * @Route("user/searchUser",name="searchUser")
     */
    function Search(Request $request, UserRepository $UserRepository){

        $searchuserForm = $this->createForm(Usersearch::class);
        
        if($searchuserForm->handleRequest($request)->isSubmitted() && $searchuserForm->isValid()) {
            $criteria = $searchuserForm->getData();
            
            $users = $UserRepository->searchUser($criteria);

            dd($users);
        }
        return $this->render('user/SearchUser.html.twig', [
            'search_form' => $searchuserForm->createView(),
            ]);
    }

}
