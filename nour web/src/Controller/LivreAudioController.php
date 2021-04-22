<?php

namespace App\Controller;

use App\Entity\CategorieSearch;
use App\Entity\LivreAudio;
use App\Form\LivreAudioType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/shift/audio")
 */
class LivreAudioController extends AbstractController
{

    /**
     * @Route("/", name="livre_audio_index", methods={"GET"})
     */
    public function index(): Response
    {


        $livreAudios = $this->getDoctrine()
            ->getRepository(LivreAudio::class)
            ->findAll();

        return $this->render('livre_audio/index.html.twig', [
            'livre_audios' => $livreAudios,
        ]);
    }
    /**
     * @Route("/front", name="livrefront", methods={"GET"})
     */
    public function showfront(): Response
    {


        $livreAudios = $this->getDoctrine()
            ->getRepository(LivreAudio::class)
            ->findAll();

        return $this->render('livre_audio/indexfront.html.twig', [
            'livre_audios' => $livreAudios,
        ]);
    }


    /**
     * @Route("/new", name="livre_audio_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $livreAudio = new LivreAudio();
        $form = $this->createForm(LivreAudioType::class, $livreAudio);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $livreAudio->getAudio();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();


            $livreAudio->setAudio($fileName);
            $file = $livreAudio->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'), $fileName);

            $entityManager = $this->getDoctrine()->getManager();

            $livreAudio->setImage($fileName);

            $entityManager->persist($livreAudio);
            $entityManager->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('livre_audio_index');
        }

        return $this->render('livre_audio/new.html.twig', [
            'livre_audio' => $livreAudio,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="livre_audio_show", methods={"GET"})
     */
    public function show(LivreAudio $livreAudio): Response
    {
        return $this->render('livre_audio/show.html.twig', [
            'livre_audio' => $livreAudio,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="livre_audio_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, LivreAudio $livreAudio): Response
    {
        $form = $this->createForm(LivreAudioType::class, $livreAudio);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $livreAudio->getAudio();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();


            $livreAudio->setAudio($fileName);
            $image= $request -> files->get ('livreAudio')['image'];
            $uploads_directory= $this->getParameter('uploads_directory');
            $filename =md5(uniqid()) . '.' . $image ->guessExtension();
            $image ->move(
                $uploads_directory,
                $filename
            );
            $livreAudio ->setImage ($filename);

            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('livre_audio_index');
        }

        return $this->render('livre_audio/edit.html.twig', [
            'livre_audio' => $livreAudio,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="livre_audio_delete", methods={"POST"})
     */
    public function delete(Request $request, LivreAudio $livreAudio): Response
    {
        if ($this->isCsrfTokenValid('delete'.$livreAudio->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($livreAudio);
            $entityManager->flush();
        }

        return $this->redirectToRoute('livre_audio_index');
    }
    /**
     * @Route("/livre/tri", name="/livre/tri")
     */
    public function Tri(Request $request,PaginatorInterface $paginator)
    {
        $em = $this->getDoctrine()->getManager();


        $query = $em->createQuery(
            'SELECT a FROM App\Entity\LivreAudio a 
            ORDER BY a.id DESC'
        );

        $livreAudios = $query->getResult();

        $livreAudios = $paginator->paginate(
            $livreAudios,
            $request->query->getInt('page',1)

        );

        return $this->render('livre_audio/index.html.twig',
            array('livre_audios' => $livreAudios));

    }

    /**
     * @Route("/map", name="map")
     */
    public function mapAction()
    {


        return $this->render('livre_audio/indexfront.html.twig', array(

        ));
    }


}
