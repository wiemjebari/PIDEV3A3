<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use App\Repository\JeuRepository;
use App\Controller\CRUDController;
use Symfony\Component\HttpFoundation\Request;
use App\Form\UserType;
use App\Form\TriUserType;
use App\Entity\PropertySearch;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Knp\Component\Pager\PaginatorInterface;


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
    public function Affiche(UserRepository $repository,FlashyNotifier $flashy){
        $flashy->primary('');
        $repo=$this->getDoctrine()->getRepository(User::class);
        $user=$repository->findAll();
        return $this->render('user/Afficheu.html.twig',
            ['user'=>$user]);
        
    }

    /**
     * @Route("/SuppUser/{id}",name="du")
     */
    function Delete($id, UserRepository $repository,FlashyNotifier $flashy){//To fix
        $flashy->primary('');
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
    function Update(UserRepository $repository,$id, Request $request,FlashyNotifier $flashy1){
        $flashy1->primary('');
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
     * @Route("user/Recherche",name="Recherche")
     */
    public function Recherche(UserRepository $repository,Request $request){
        $user="";
        $data="";
        $data2="";
        $data3="";
        $data=$request->get('search');
        if($data != NULL)
        {
        $user=$repository->findBy(['nom' => $data]); //check this nsc later
        }
        $data2=$request->get('search2');
        if($data2 != NULL)
        {
        $user=$repository->findBy(['prenom' => $data2]); //check this nsc later
        }
        $data3=$request->get('search3');
        if($data3 != NULL)
        {
        $user=$repository->findBy(['sexe' => $data3]); //check this nsc later
        }
        if(($data != NULL) && ($data2 != NULL) && ($data != NULL)){
           $user=$repository->findBy(['nom' => $data,'prenom' => $data2,'sexe' => $data3]); 
        }
        
        return $this->render('user/SearchUser.html.twig', ['user'=>$user]);
    }



    /**
     * @Route("user/triUser",name="triUser")
     */
    public function listUser(Request $request,UserRepository $repository,FlashyNotifier $flashy)
    {

        

        $repo=$this->getDoctrine()->getRepository(User::class);
        $user=$repository->findAll();

        $form=$this->createForm(TriUserType::class);
        $form->add('Tri',SubmitType::class);
        $form->handleRequest($request);


        if($form->isSubmitted() && $form->isValid()){

        
        
        $choix= $form->get('Tritype')->getData();
        if($choix == 'Par Nom_Asc')
        {
            $flashy->primarydark('Tri par nom Ascendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByNomAsc();
        }
        else if($choix == 'Par Nom_Desc')
        {
            $flashy->info('Tri par nom Decendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByNomDesc();
        }
        else if($choix == 'Par Prenom_Asc')
        {
            $flashy->primarydark('Tri par Prenom Ascendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByPreomAsc();
        }
        else if($choix == 'Par Prenom_Desc')
        {
            $flashy->info('Tri par Prenom Decendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByPreomDesc();
        }
        else if($choix == 'Par Genre_Asc')
        {
            $flashy->primarydark('Tri par Genre Ascendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByGenreAsc();
        }
        else if($choix == 'Par Genre_Desc')
        {
            $flashy->info('Tri par Genre Decendant');
            $user = $this->getDoctrine()
            ->getRepository(User::class)
            ->findUserByGenreDesc();
        }
        

        
            //$em=$this->getDoctrine()->getManager();
            //$em->persist($user);
            //$em->flush();
            //return $this->redirectToRoute('AfficheUser');
            return $this->render('user/listUser.html.twig',[
                'form'=>$form->createView(),
                'user' => $user
            ]);
        }

        $flashy->primary('');

        return $this->render('user/listUser.html.twig',[
            'form'=>$form->createView(),
            'user' => $user
        ]);


        
        /*
        return $this->render('user/listUser.html.twig', ['user' => $user]);
        */
    }
    

    /**
     * @Route("user/nombreUser",name="nombreUser")
     */
    public function countUser(){
        $number = $this->getDoctrine()
                       ->getRepository(User::class)
                       ->numberOfUsers();
        return $this->render('user/statistiqueu.html.twig',
                     ['nombre' => $number]);
    }

    /**
     * @Route("user/Chart0", name="Chart00")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function gotochart(){    
        return $this->render('user/chart0.html.twig');
    }

    /**
     * @Route("user/Chart11", name="Chart11")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statAction()//Essaie d'ajouter même plus de Pie Charts^^
    {

        $pieChart = new PieChart();

        $number1 = $this->getDoctrine()
                       ->getRepository(User::class)
                       ->numberOfUsersGenre1();
        $number2 = $this->getDoctrine()
                       ->getRepository(User::class)
                       ->numberOfUsersGenre2();
        $a=5-$number1;
        $b=5-$number2;

        

        $pieChart->getData()->setArrayToDataTable( 
                [['Users', 'Pie Partie'],
                ['Homme',5-$a],
                ['Femme',5-$b],
                ]
            );
 
        $pieChart->getOptions()->setTitle('users selon le genre');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(500);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
 
         
        return $this->render('user/chart.html.twig', array(
                'piechart' => $pieChart,
            )
 
        );
    }


    /**
     * @Route("user/Chart22", name="Chart22")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statAction2()//Essaie d'ajouter même plus de Pie Charts^^
    {

        $pieChart = new PieChart();

        $number1 = $this->getDoctrine()
                       ->getRepository(User::class)
                       ->numberOfUserByRole1();
        $number2 = $this->getDoctrine()
                       ->getRepository(User::class)
                       ->numberOfUserByRole2();

        $a=5-$number1;
        $b=5-$number2;
        
        

        $pieChart->getData()->setArrayToDataTable( 
                [['Users', 'Pie Partie'],
                ['Admin',5-$a],
                ['Client',5-$b],
                ]
            );
 
        $pieChart->getOptions()->setTitle('users selon le Role');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(500);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
 
         
        return $this->render('user/chart2.html.twig', array(
                'piechart' => $pieChart,
            )
 
        );
    }

}
