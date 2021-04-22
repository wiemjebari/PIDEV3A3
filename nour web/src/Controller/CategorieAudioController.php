<?php

namespace App\Controller;

use App\Entity\CategorieAudio;
use App\Entity\CategorieSearch;
use App\Entity\PropertySearch;
use App\Form\CategorieAudioType;
use App\Form\PropertySearchType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\Request;






/**
 * @Route("/categorie/audio")
 */
class CategorieAudioController extends AbstractController
{
    /**
     * @Route("/", name="categorie_audio_index", methods={"GET"})

     */
    public function index(PaginatorInterface $paginator,Request $request): Response
    {
        $propertySearch = new PropertySearch();
        $form = $this->createForm(PropertySearchType::class, $propertySearch);
        $form->handleRequest($request);
        //initialement le tableau des articles est vide,
        //c.a.d on affiche les articles que lorsque l'utilisateur clique sur le bouton rechercher
        $categorieAudios = [];

        if ($form->isSubmitted() && $form->isValid()) {
            $nom = $propertySearch->getNom();
            if ($nom != "")
                $categorieAudios = $this->getDoctrine()->getRepository(CategorieAudio::class)->findBy(['nom' => $nom]);


            else
                $categorieAudios = $this->getDoctrine()
                    ->getRepository(CategorieAudio::class)
                    ->findAll();
        }


            return $this->render('categorie_audio/index.html.twig', [ 'form' =>$form->createView(),
                'categorie_audios' => $categorieAudios
            ]);

    }



    /**
     * @Route("/new", name="categorie_audio_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $categorieAudio = new CategorieAudio();
        $form = $this->createForm(CategorieAudioType::class, $categorieAudio);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($categorieAudio);
            $entityManager->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );
            return $this->redirectToRoute('categorie_audio_index');
        }

        return $this->render('categorie_audio/new.html.twig', [
            'categorie_audio' => $categorieAudio,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="categorie_audio_show", methods={"GET"})
     */
    public function show(CategorieAudio $categorieAudio): Response
    {
        return $this->render('categorie_audio/show.html.twig', [
            'categorie_audio' => $categorieAudio,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="categorie_audio_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, CategorieAudio $categorieAudio): Response
    {
        $form = $this->createForm(CategorieAudioType::class, $categorieAudio);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('categorie_audio_index');
        }

        return $this->render('categorie_audio/edit.html.twig', [
            'categorie_audio' => $categorieAudio,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="categorie_audio_delete", methods={"POST"})
     */
    public function delete(Request $request, CategorieAudio $categorieAudio): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorieAudio->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($categorieAudio);
            $entityManager->flush();
        }

        return $this->redirectToRoute('categorie_audio_index');
    }

}
