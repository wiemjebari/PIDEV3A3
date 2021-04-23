<?php

namespace App\Controller;

use App\Entity\Conseils;
use App\Form\ConseilsType;
use App\Repository\ConseilsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\String\Slugger\SluggerInterface;

/**
 * @Route("/conseils")
 */
class ConseilsController extends AbstractController
{
    /**
     * @Route("/", name="conseils_index", methods={"GET"})
     */
    public function index(ConseilsRepository $conseilsRepository): Response
    {
        return $this->render('conseils/index.html.twig', [
            'conseils' => $conseilsRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="conseils_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $conseil = new Conseils();
        $form = $this->createForm(ConseilsType::class, $conseil);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $imageFile->move(
                        $this->getParameter('conseil_image'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }


                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $conseil->setImage($newFilename);
            }
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($conseil);
            $entityManager->flush();

            return $this->redirectToRoute('conseils_index');
        }

        return $this->render('conseils/new.html.twig', [
            'conseil' => $conseil,
            'form' => $form->createView(),
        ]);
    }

//    /**
//     * @Route("/{id_article}", name="conseils_show", methods={"GET"})
//     */
//    public function show(Conseils $conseil): Response
//    {
//        return $this->render('conseils/show.html.twig', [
//            'conseil' => $conseil,
//        ]);
//    }

    /**
     * @Route("/{id_article}/edit", name="conseils_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Conseils $conseil): Response
    {
        $form = $this->createForm(ConseilsType::class, $conseil);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $imageFile->move(
                        $this->getParameter('conseil_image'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }


                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $conseil->setImage($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('conseils_index');
        }

        return $this->render('conseils/edit.html.twig', [
            'conseil' => $conseil,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/delete/{id_article}", name="conseils_delete", methods={"GET","POST"})
     */
    public function delete(Request $request, Conseils $conseil, ConseilsRepository $conseilsRepository): Response
    {
            $entityManager = $this->getDoctrine()->getManager();
            $conseilRepo = $conseilsRepository->find($conseil->getIdArticle());
            $entityManager->remove($conseilRepo);
            $entityManager->flush();

        return $this->redirectToRoute('conseils_index');
    }


}
