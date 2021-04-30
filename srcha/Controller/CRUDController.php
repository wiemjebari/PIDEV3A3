<?php

namespace App\Controller;

use App\Entity\Jeu;
use App\Entity\PropertySearch;
use App\Repository\JeuRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Form\CRUDType;
use App\Form\TriJeuType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use MercurySeries\FlashyBundle\FlashyNotifier;

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


    /**
     * @Route("crud/Recherche",name="Recherche1")
     */
    public function Search1(JeuRepository $repository,Request $request){      
        $jeu="";  
        $data=$request->get('search');
        if($data != NULL)
        {
            $jeu=$repository->findBy(['nom_cat' => $data]); //check this nsc later
        }
        $data2=$request->get('search2');
        if($data2 != NULL)
        {
            $jeu=$repository->findBy(['nom_jeu' => $data2]); //check this nsc later
        }
        
        $data3=$request->get('search3');
        if($data3 != NULL)
        {
            $jeu=$repository->findBy(['niv_diff' => $data3]); //check this nsc later
        }           
        return $this->render('crud/SearchJeu.html.twig', ['jeu'=>$jeu]);
        
    }
    /*public function Search2(JeuRepository $repository,Request $request){        
        $data2=$request->get('search2');
        $jeu2=$repository->findBy(['nom_jeu' => $data2]); //check this nsc later
        return $this->render('crud/SearchJeu.html.twig', ['jeu'=>$jeu2]);
        
    }
    public function Search3(JeuRepository $repository,Request $request){        
        $data3=$request->get('search3');
        $jeu3=$repository->findBy(['niv_diff' => $data3]); //check this nsc later
        return $this->render('crud/SearchJeu.html.twig', ['jeu'=>$jeu3]);
        
    }*/

      

    /**
     * @Route("crud/triJeu",name="triJeu")
     */
    public function listJeu(Request $request,JeuRepository $repository,FlashyNotifier $flashy)
    {

        
        //$flashy->info('Tri Page');

        $repo=$this->getDoctrine()->getRepository(Jeu::class);
        $jeu=$repository->findAll();

        $form=$this->createForm(TriJeuType::class);
        $form->add('Tri',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){

        $choix= $form->get('Tritype')->getData();

        if($choix == 'Par Nom_Asc'){           
            $flashy->info('Tri par nom Ascendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNamejeuAsc();
        }
        else if($choix == 'Par Nom_Desc'){
            $flashy->primarydark('Tri par nom Descendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNamejeuDesc();
        }
        else if($choix == 'Par Cat_Asc'){
            $flashy->info('Tri par Category Ascendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNamecatAsc();
        }
        else if($choix == 'Par Cat_Desc'){
            $flashy->primarydark('Tri par Category Descendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNamecatDesc();
        }
        else if($choix == 'Par Niv_Diff_Asc'){
            $flashy->info('Tri par Niv de difficulté Ascendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNivdiffAsc();
        }
        else if($choix == 'Par Niv_Diff_Desc'){
            $flashy->primarydark('Tri par Niv de difficulté Descendant');
            $jeu = $this->getDoctrine()
            ->getRepository(Jeu::class)
            ->findJeuByNivdiffDesc();
        }

        return $this->render('crud/listJeu.html.twig',[
            'form'=>$form->createView(),
            'jeu' => $jeu
        ]);

    }
        
    $flashy->primary('');

        return $this->render('crud/listJeu.html.twig',[
            'form'=>$form->createView(),
            'jeu' => $jeu
        ]);
    }
    

    /**
     * @Route("crud/nombreJeu",name="nombreJeu")
     */
    public function countJeu(){
        $number = $this->getDoctrine()
                       ->getRepository(Jeu::class)
                       ->numberOfJeux();
        return $this->render('crud/statistiquejeu.html.twig',
                     ['nombre' => $number]);
    }


    /**
     * @Route("crud/Chart0", name="Chart0")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function gotochart(){    
        return $this->render('crud/chart0.html.twig');
    }



    /**
     * @Route("crud/Chart", name="Chart4")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statAction()//Essaie d'ajouter même plus de Pie Charts^^
    {

        $pieChart = new PieChart();

        $number1 = $this->getDoctrine()
                       ->getRepository(Jeu::class)
                       ->numberOfJeuxdiff1();
        $number2 = $this->getDoctrine()
                       ->getRepository(Jeu::class)
                       ->numberOfJeuxdiff2();
        $number3 = $this->getDoctrine()
                       ->getRepository(Jeu::class)
                       ->numberOfJeuxdiff3();
        $a=5-$number1;
        $b=5-$number2;
        $c=5-$number3;

        

        $pieChart->getData()->setArrayToDataTable( 
                [['Jeux', 'Pie Partie'],
                ['niv1',5-$a],
                ['niv2',5-$b],
                ['niv3',5-$c]
                ]
            );
 
        $pieChart->getOptions()->setTitle('Jeux selon niveau de difficulté');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(500);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
 
         
        return $this->render('crud/chart.html.twig', array(
                'piechart' => $pieChart,
            )
 
        );
    }

    /**
     * @Route("crud/Chart2", name="Chart2")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statAction2()//Essaie d'ajouter même plus de Pie Charts^^
    {
        $pieChart2 = new PieChart();

        $name1 =   $this->getDoctrine()
                   ->getRepository(Jeu::class)
                   ->numberOfJeuxnom1();
        
        $name2 =   $this->getDoctrine()
                   ->getRepository(Jeu::class)
                   ->numberOfJeuxnom2();

        $d=5-$name1;
        $e=5-$name2;

        $pieChart2->getData()->setArrayToDataTable( 
            [['Jeux', 'Pie Partie'],
            ['Nom1',5-$d],
            ['Nom2',5-$e]
            ]
        );
 
        $pieChart2->getOptions()->setTitle('Jeux selon Nom du jeu');
        $pieChart2->getOptions()->setHeight(500);
        $pieChart2->getOptions()->setWidth(500);
        $pieChart2->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart2->getOptions()->getTitleTextStyle()->setFontSize(25); 


        return $this->render('crud/chart2.html.twig', array(
                'piechart2' => $pieChart2,
            )
 
        );
    }

    /**
     * @Route("crud/Chart3", name="Chart3")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statAction3()//Essaie d'ajouter même plus de Pie Charts^^
    {
        $pieChart3 = new PieChart();

        $cat1 =   $this->getDoctrine()
                   ->getRepository(Jeu::class)
                   ->numberOfJeuxcat1();
        
        $cat2 =   $this->getDoctrine()
                   ->getRepository(Jeu::class)
                   ->numberOfJeuxcat2();

        $cat3 =   $this->getDoctrine()
                   ->getRepository(Jeu::class)
                   ->numberOfJeuxcat3();

        $f=3-$cat1;
        $g=3-$cat2;
        $h=3-$cat3;

        $pieChart3->getData()->setArrayToDataTable( 
            [['Jeux', 'Pie Partie'],
            ['Meditation',3-$f],
            ['Adventure',3-$g],
            ['Yoga',3-$h]
            ]
        );
 
        $pieChart3->getOptions()->setTitle('Jeux selon Nom de catégorie');
        $pieChart3->getOptions()->setHeight(500);
        $pieChart3->getOptions()->setWidth(500);
        $pieChart3->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart3->getOptions()->getTitleTextStyle()->setFontSize(25); 


        return $this->render('crud/chart3.html.twig', array(
                'piechart3' => $pieChart3,
            )
 
        );
    }


}
