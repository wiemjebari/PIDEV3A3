<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Centre;
use App\Form\ReservationType;
use App\Form\CentreType;
use App\Repository\ReservationRepository;
use App\Repository\CentreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Entity;

/**
 * @Route("/reservation")
 */
class ReservationController extends AbstractController
{
    /**
     * @Route("/", name="reservation_index", methods={"GET"})
     */
    public function index(ReservationRepository $reservationRepository): Response
    {
        return $this->render('fronta.html.twig', [
            'reservations' => $reservationRepository->findAll(),
        ]);
    }


    /**
     * @Route("/{id}/new_res", name="new_res", methods={"GET","POST"})
     * @Entity("centre", expr="repository.find(id)")
     */
public function newReservation( Centre $centre, Request $request, EntityManagerInterface $em)
{
    $reservation = new Reservation();
    $form = $this->createForm(ReservationType::class, $reservation);
    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
       
        $centre = $form->getData();
        $em->persist($reservation);
        $em->persist($centre);
        $em->flush();
       
        return $this->redirectToRoute('reservation_index',array('id' => $centre->getId()));
    }
    $centr = $this->createForm(CentreType::class, $centre);
    
    return $this->render('reservation/new.html.twig', [
        'centre' => $centr->createView(),
        'reservation' => $reservation,
        'form' => $form->createView()
    ]);
}

    ///////////////////////////////////////////////////////
    
    
     
   

     /**
     *  @param ReservationRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/show",name="reservation_show")
     */
    public function show(ReservationRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Reservation::class);
        $reservation=$repository->findAll();
        return $this->render('reservation/show.html.twig',
            ['reservations'=>$reservation]);
        
    }

    
    /**
     * @Route("/{nomCentre}/edit", name="reservation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reservation $reservation): Response
    {
        $form = $this->createForm(ReservationType::class, $reservation);
       
        $form->add('Modifier' , SubmitType::class);
       
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reservation_index');
        }

        return $this->render('reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

      /**
     * @Route("/{nomCentre}", name="reservation_delete", methods={"POST"})
     */
    public function delete($nomCentre, ReservationRepository $repository)
    {
        $reservation=$repository->find($nomCentre);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reservation);
        $em->flush();
       
        return $this->redirectToRoute('reservation_index');
        
    
    }
}
