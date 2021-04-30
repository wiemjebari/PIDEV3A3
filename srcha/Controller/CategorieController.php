<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Produit;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use App\Repository\ProduitRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;

/**
 * @Route("/categorie")
 */
class CategorieController extends AbstractController
{
    /**
     * @Route ("/stats",name="categorie_stats")

     */
    public function statistiques(CategorieRepository  $categRepo ,ProduitRepository $produitRepo){
        //on va chercher toutes les catégories
        $produits=$produitRepo->findAll();
        $categories=$categRepo->findAll();
        $categNom=[];
        $categColor=[];
        $categProduit=[];
        $categCount=[];
        $cafe = $produitRepo->findBy(array('idcategorie' => 'cafe'));
        $restaurant = $produitRepo->findBy(array('idcategorie' => 'restaurant'));
        $supermarche = $produitRepo->findBy(array('idcategorie' => 'supermarche'));
        foreach ($produits as $produit){
        foreach ($categories as $categorie){
            $categProduit[]=$produit->getIdcategorie();
            $categNom[]= $categorie->getNomCategorie();
            $categColor[]=$categorie->getColor();
            $categCount[]=count($categProduit);
        }}
        return $this->render('categorie/stats.html.twig',[
            'categNom'=>json_encode($categNom),
            'categColor'=>json_encode($categColor),
            'categCount'=>json_encode($categCount),
            ['idcategorie', 'Number of Products', 'Total'],
            ['OnCafe', count($cafe), count($cafe) + count($supermarche) + count($restaurant) ],
            ['Insupermarche', count($supermarche), count($cafe) + count($supermarche) + count($restaurant)],
            ['Restaurant', count($restaurant), count($cafe) + count($supermarche) + count($restaurant) ],

        ]);



    }
    public function chartPieAction(Request $request)
    {
        $pieChart = new PieChart();
        $em = $this->getDoctrine();
        $cafe = $em->getRepository(Produit::class)->findBy(array('idcategorie' => 'cafe'));
        $supermarche = $em->getRepository(Produit::class)->findBy(array('idcategorie' => 'supermarche'));
        $restaurant = $em->getRepository(Produit::class)->findBy(array('idcategorie' => 'restaurant'));


        $pieChart->getData()->setArrayToDataTable([
            ['idcategorie', 'Number of Products', 'Total'],
            ['OnCafe', count($cafe), count($cafe) + count($supermarche) + count($restaurant) ],
            ['Insupermarche', count($supermarche), count($cafe) + count($supermarche) + count($restaurant)],
            ['Restaurant', count($restaurant), count($cafe) + count($supermarche) + count($restaurant) ],


        ]);
        $pieChart->getOptions()->setTitle('Produits stat ');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        return $this->render('@categorie\stats.html.twig', array('piechart' => $pieChart));

    }
    /**
     * @Route("/", name="categorie_index", methods={"GET"})
     */
    public function index(): Response
    {
        $categories = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();

        return $this->render('categorie/index.html.twig', [
            'categories' => $categories,
        ]);
    }

    /**
     * @Route("/new", name="categorie_new", methods={"GET","POST"})
     */
    public function new( FlashyNotifier $flashy ,Request $request): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($categorie);
            $entityManager->flush();
            $flashy->success('Event created!', 'http://your-awesome-link.com');
            return $this->redirectToRoute('categorie_index');
        }

        return $this->render('categorie/new.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{idc}", name="categorie_show", methods={"GET"})
     */
    public function show(Categorie $categorie): Response
    {
        return $this->render('categorie/show.html.twig', [
            'categorie' => $categorie,
        ]);
    }

    /**
     * @Route("/{idc}/edit", name="categorie_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Categorie $categorie): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('categorie_index');
        }

        return $this->render('categorie/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idc}", name="categorie_delete", methods={"POST"})
     */
    public function delete(Request $request, Categorie $categorie): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorie->getIdc(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($categorie);
            $entityManager->flush();
        }

        return $this->redirectToRoute('categorie_index');
    }

}
