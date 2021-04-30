<?php

namespace App\Controller;

use App\Entity\SearchData;
use App\Entity\Produit;
use App\Form\ProduitType;
use App\Form\PropertySearchType;
use App\Form\SearchType;
use App\Repository\ProduitRepository;

use Doctrine\Persistence\ObjectManager;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
use App\Entity\PropertySearch;
use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/produit")
 */
class ProduitController extends AbstractController
{

    /**
     * @Route("Imprimer/{id}", name="Imprimer", methods={"GET","POST"})
     */
    public function Imprimer (Produit $produit,ProduitRepository $produitRepository): Response
    {

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Roboto');
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('produit/pdf.html.twig', [
            'produits' => $produitRepository->findByid($produit),
        ]);
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Produit.imprimer", [
            "Attachment" => true
        ]);
    }


    /**
     * @Route("/", name="produit_index", methods={"GET"})
     */
    public function index(): Response
    {
        $produits = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();

        return $this->render('produit/index.html.twig', [
            'produits' => $produits,
        ]);
    }
    /**
     * @var ProduitRepository
     */
    private $repository;
    /**
     * @Route("/indexfront", name="produit_indexfront", methods={"GET"})
     */
    public function indexfront( ProduitRepository $repository,Request $request): Response
    {
        $data = new SearchData();
        $data->page=$request->get('page',1);
        $form=$this->createForm(SearchType::class,$data);
        $form->handleRequest($request);


        $produits = $repository->findSearch($data);





        return $this->render('produit/indexfront.html.twig', [
            'produits' => $produits,
            'form'     => $form->createView()
        ]);
    }


    /**
     * @Route("/new", name="produit_new", methods={"GET","POST"})
     */
    public function new( FlashyNotifier $flashy ,Request $request): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $produit->getPhoto();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'), $fileName);

            $entityManager = $this->getDoctrine()->getManager();
            $produit->setPhoto($fileName);
            $entityManager->persist($produit);
            $entityManager->flush();
            $flashy->success('Produit Ajouté !', 'produit/indexfront');
            return $this->redirectToRoute('produit_index');
        }

        return $this->render('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="produit_show", methods={"GET"})
     */
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="produit_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Produit $produit): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_index');
        }

        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="produit_delete", methods={"POST"})
     */
    public function delete(Request $request, Produit $produit): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($produit);
            $entityManager->flush();
        }

        return $this->redirectToRoute('produit_index');
    }

    /**
     * @Route("/{id}", name="produit_showfront", methods={"GET"})
     */
    public function showfront(Produit $produit): Response
    {
        return $this->render('produit/showfront.html.twig', [
            'produit' => $produit,
        ]);
    }

}
