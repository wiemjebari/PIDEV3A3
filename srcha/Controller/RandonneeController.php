<?php

namespace App\Controller;
use App\Entity\Randonnees;
use App\Form\RandonneesType;
use App\Repository\RandonneeRepository;
use App\Repository\ReservationrandonneesRepository;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class RandonneeController extends AbstractController
{
    /**
     * @Route("/randonnees", name="randonnees")
     */
    public function index(RandonneeRepository $repository): Response
    {
        $randonnees=$repository->OrderByDate();
        return $this->render('randonnee/indexfront.html.twig', [
            'randonnee'=>$randonnees,
        ]);
    }

    /**
     *  @Route("admin/randonnees",name="Affiche")
     */
    public function Affiche(RandonneeRepository $repository){
        $randonnees=$repository->findAll();
        return $this->render('randonnee/Affiche.html.twig',
            ['randonnee'=>$randonnees]);
    }

    /**
     * @Route("/Supp/{id}",name="d")
     */
    function Delete($id, RandonneeRepository $repository,ReservationrandonneesRepository $reservationRepository){
        $randonnee=$repository->find($id);
        $reservations=$reservationRepository->findBy(array('Randonnee'=>$randonnee));
        if($reservations){
            foreach ($reservations as $item){
                $em=$this->getDoctrine()->getManager();
                $em->remove($item);
                $em->flush();
            }
        }
        $em=$this->getDoctrine()->getManager();
        $em->remove($randonnee);
        $em->flush();
        return $this->redirectToRoute('Affiche');
    }

        /**
         * @Route("admin/Randonnee/Add", name="add")
         */

    function Add(Request $request){
        $randonnee= new randonnees();
        $form=$this->createForm(RandonneesType::class , $randonnee);
        $form->add('Ajouter',SubmitType::class);

        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('photo')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();

                try {
                    $imageFile->move(
                        'C:/xampp/htdocs/EspritClub/public/uploads',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                $randonnee->setPhoto($newFilename);
            }

            $em=$this->getDoctrine()->getManager();
            $em->persist($randonnee);
            $em->flush();
            return $this->redirectToRoute('Affiche');
        }
        return $this->render('randonnee/Add.html.twig',[
            'form'=>$form->createView(),
        ]);

    }

    /**
     * @Route("admin/randonnee/Update/{id}",name="update")
     */
    function Update(RandonneeRepository $repository, $id, Request $request){
        $randonnee=$repository->find($id);
        $form=$this->createForm(RandonneesType::class,$randonnee);
        $form->add('Modifier' , SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('photo')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();

                try {
                    $imageFile->move(
                        'C:/xampp/htdocs/EspritClub/public/uploads',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exceptionsa if something happens during file upload
                }

                $randonnee->setPhoto($newFilename);
            }

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Affiche');
        }
        return $this->render('randonnee/Update.html.twig',
        [
            'form'=>$form->createView(),
            'randonnee'=>$randonnee,
        ]);
    }

    /**
     * @Route("/searchRandonnee",name="search")
     */
    function search(Request $request,RandonneeRepository $randonneRepo)
    {
        $requestString = $request->get('q');

        $entities = $randonneRepo->findEntitiesByString($requestString);

        if(!$entities) {
            $result['entities']['error'] = "Aucune Randonnee trouvée";
        } else {
            $result['entities'] = $this->getRealEntities($entities);
        }
        return new Response(json_encode($result));
    }

    /**
     * @Route("/filterRandonnee",name="filter")
     */
    function filter(Request $request,RandonneeRepository $randonneRepo)
    {
        $requestPrix = $request->get('q');

        $entities = $randonneRepo->filterParPrix($requestPrix);

        if(!$entities) {
            $result['entities']['error'] = "Aucune Randonnee trouvée";
        } else {
            $result['entities'] = $this->getRealEntities($entities);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($randonnees){
        foreach ($randonnees as $r){
            $realEntities[$r->getId()] = [$r->getLieu(),$r->getDate(), $r->getHeureDepart(),$r->getHeureRetour(),$r->getNbrPlaces(),$r->getPhoto(),$r->getPrix(),$r->getDate()->format('d'),$r->getDate()->format('m'),$r->getDate()->format('y')];
        }
        return $realEntities;
    }

    /**
     * @Route("/triRandonnee",name="triRando")
     */
    function OrderByPrixDql(RandonneeRepository $randonneRepo)
    {
        $entities=$randonneRepo->OrderByPrix();
       if(!$entities) {
            $result['entities']['error'] = "Aucune Randonnee trouvée";
        } else {
            $result['entities'] = $this->getRealEntities($entities);
        }
        return new Response(json_encode($result));
    }

    /**
     *  @Route("admin/randonnee/gain/{id}",name="AfficheGain")
     */
    public function Gain(RandonneeRepository $repository,ReservationrandonneesRepository $reservationRepo,$id){
        $randonnee=$repository->find($id);
        $liste=$reservationRepo->findBy(array('Randonnee'=>$randonnee,'etat'=>"CONFIRME"));
        $nbr_resevation=0;
        foreach ($liste as $row){
            $nbr_resevation+=$row->getNbrPlaces();
        }
        $gain=$nbr_resevation*$randonnee->getPrix();
        return $this->render('randonnee/gain.html.twig',
            ['gain'=>$gain

            ]);

    }
}

