<?php

namespace App\Controller;

use App\Entity\Randonnees;
use App\Entity\Avis;
use App\Entity\Reservationrandonnees;
use App\Form\RandonneesType;
use App\Form\AvisType;
use App\Form\ReservationrandonneesType;
use App\Repository\RandonneeRepository;
use App\Repository\ReservationrandonneesRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReservationRandonneesController extends AbstractController
{
    /**
     * @Route("/admin/reservations", name="reservationadmin")
     */
    public function index(ReservationrandonneesRepository $repository): Response
    {
        $reservations=$repository->findAll();
        return $this->render('reservation_randonnees/indexadmin.html.twig', [
            'reservations'=>$reservations,
        ]);
    }

    /**
     *  @Route("/mesReservations",name="mesReservation")
     */
    public function Affiche(ReservationrandonneesRepository $repository){
        $reservations=$repository->findBy(array('User'=>3));
        return $this->render('reservation_randonnees/index.html.twig',
            ['reservations'=>$reservations]);

    }

    /**
     * @Route("/SuppReservation/{id}",name="deleteReservation")
     */
    function Delete($id, ReservationrandonneesRepository $repository){
        $reservation=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reservation);
        $em->flush();
        return $this->redirectToRoute('mesReservation');

    }

    /**
     * @Route("reservation/Update/{id}",name="updateReservation")
     */
    function Update(ReservationrandonneesRepository $repository, $id, Request $request){
        $reservation=$repository->find($id);
        $form=$this->createForm(ReservationrandonneesType::class,$reservation);
        $form->add('Modifier' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('mesReservation');
        }
        return $this->render('reservation_randonnees/Update.html.twig',
            [
                'form'=>$form->createView(),
                'reservation'=>$reservation,
            ]);
    }

    /**
     * @Route("addReservation/{id}", name="addReservation")
     */

    function Add(Request $request,$id,RandonneeRepository $repository,UserRepository $userRepository){
        $reservation= new Reservationrandonnees();
        $form=$this->createForm(ReservationrandonneesType::class , $reservation);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        $randonne=$repository->find($id);
        if($form->isSubmitted() && $form->isValid()){

            $reservation->setRandonnee($randonne);
            $reservation->setEtat("ENATTENTE");
            $user=$userRepository->find(3);
            $reservation->setUser($user);
            $reservation->setDate(new \DateTime('now'));
            $em=$this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();
            return $this->redirectToRoute('mesReservation');
        }
        return $this->render('reservation_randonnees/Add.html.twig',[
            'form'=>$form->createView(),
            'randonnee'=>$randonne,
        ]);

    }
    /**
     * @Route("/reservation/confirmer/{id}",name="confirmerReservation")
     */
    function Confirmer(ReservationrandonneesRepository $repository, $id, Request $request){
        $reservation=$repository->find($id);
        $reservation->setEtat("CONFIRME");
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        //$repository->sms();
        return $this->redirectToRoute('reservationadmin');

    }

    /**
     * @Route("/reservation/refuser/{id}",name="refuserReservation")
     */
    function refuser(ReservationrandonneesRepository $repository, $id, Request $request){
        $reservation=$repository->find($id);
        $reservation->setEtat("REFUSE");
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        return $this->redirectToRoute('reservationadmin');

    }

    /**
     * @Route("avisRandonnee/{id}", name="avisRandonnee")
     */

    function AvisRando(Request $request,$id,RandonneeRepository $repository,UserRepository $userRepository){
        $avis= new Avis();
        $form=$this->createForm(AvisType::class , $avis);
        $form->add('Envoyer',SubmitType::class);
        $form->handleRequest($request);
        $randonnee=$repository->find($id);
        if($form->isSubmitted() && $form->isValid()){

            $avis->setIdRandonne($randonnee->getId());
            $user=$userRepository->find(3);
            $avis->setIdUser($user->getid_client());
            $em=$this->getDoctrine()->getManager(); 
            $em->persist($avis); 
            $em->flush();
            return $this->redirectToRoute('mesReservation');
        }
        return $this->render('reservation_randonnees/avisRandonnee.html.twig',[
            'form'=>$form->createView(),
            'randonnee'=>$randonnee,
        ]);

    }


 }
