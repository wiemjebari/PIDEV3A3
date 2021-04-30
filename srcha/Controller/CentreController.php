<?php

namespace App\Controller;

use App\Entity\Centre;
use App\Form\CentreType;
use App\Repository\CentreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Doctrine\ORM\EntityManagerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\VarDumper\Cloner\Data;
use Swift_Mailer;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


/**
 * @Route("/centre")
 */
class CentreController extends AbstractController
{
    /**
     * @Route("/", name="centre_index", methods={"GET"})
     */
    public function index(CentreRepository $centreRepository): Response
    {
        return $this->render('basea.html.twig', [
            'centres' => $centreRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="centre_new")
     * @param Swift_Mailer $mailer
     */
    public function new(Request $request , \Swift_Mailer $mailer)
    {
        $centre = new Centre();
        $form = $this->createForm(CentreType::class, $centre);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        
        if ($form->isSubmitted() && $form->isValid()) {
            $new=$form->getData();
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
                $centre->setPhoto($newFilename);}
            $entityManager = $this->getDoctrine()->getManager();
          

            $entityManager->persist($centre);
            $this->addFlash('messge','centre ajoute');
         /**   $entityManager->flush();**/
            ////////envoie  du mail
            $message = (new \Swift_Message('Nouveau contact'))
            ///expediteur
            ->setFrom('aleeboukesra@gmail.com')
            ///destinataire
            ->setTo('asma.bensaid@esprit.tn')
            //message avec vue twig
            ->setBody(
                $this->renderView(
                    'emails/contact.html.twig',compact('new')
                ),
                'text/html'
                

             ) ;
            
           
            $mailer->send($message);
            $this->addFlash('message', 'envoye');

            return $this->redirectToRoute('centre_index');
        }

        return $this->render('Add.html.twig', [
            'centre' => $centre,
            'form' => $form->createView(),
        ]);
    }


    /**
     *  @param CentreRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/show",name="centre_show")
     */
    public function show(CentreRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Centre::class);
        $centre=$repository->findAll();
        return $this->render('Affiche.html.twig',
            ['centre'=>$centre]);
        
    }
    /**
     *  @param CentreRepository $repository
     *  @return \Symfony\Component\HttpFoundation\Response
     *  @Route("/showcent",name="centref_show")
     */
    public function showf(CentreRepository $repository){
        $repo=$this->getDoctrine()->getRepository(Centre::class);
        $centre=$repository->findAll();
        return $this->render('reservation/showcent.html.twig',
            ['centre'=>$centre]);
        
    }
    /**
     * @Route("/{id}/edit", name="centre_edit", methods={"GET","POST"})
     */
    /*
    public function edit(Request $request, Centre $centre,$id,CentreRepository $repository): Response
    {
        $em = $this->getDoctrine()->getManager();
        $form = $this->createForm(CentreType::class);
       
        $form->add('Modifier' , SubmitType::class);

        $cent=$repository->find($id);
       
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $$em->flush();

            return $this->redirectToRoute('centre_index');
        }

        return $this->render('centre/edit.html.twig', [
            'centres' => $centre,
            'form' => $form->createView(),
        ]);
    }
*/
    /**
     * @Route("/{id}/edit", name="centre_edit", methods={"GET","POST"})
     */
public function edit(Centre $centre, Request $request, EntityManagerInterface $em)
{
    $form = $this->createForm(CentreType::class, $centre);
    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
       
        $centre = $form->getData();
        $em->persist($centre);
        $em->flush();
       
        return $this->redirectToRoute('centre_index', [
            'id' => $centre->getId(),
        ]);
    }
    return $this->render('centre/edit.html.twig', [
        'form' => $form->createView()
    ]);
}
 

   
     /**
     * @Route("/centre/delete/{id}", name="centre_delete")
     * @return Response
     */
    function delete($id,CentreRepository $repository){
        $centre=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($centre);
        $em->flush();
        return $this->redirectToRoute('centre_index');
        return new Response ('element supprime');

    }
/**
 *
 * @param CentreRepository $repository
 *@return \Symfony\Component\HttpFoundation\Response
 *  @Route("/show/trie_mail" ,name = "trie")
 */
    public function OrderByMailDql(CentreRepository $repository){
        $centre=$repository->OrderByMail();
        return $this->render('Affiche.html.twig',
            ['centre'=>$centre]);
        
    }


    /**
     *  @param Request $request
     * @param NormalizerInterface $normalizer
     * @return Response
     * @Route("/show/searchCentre",name="search")
     */
    function search(Request $request,CentreRepository $centreRepo,NormalizerInterface $normalizer)
    {
        $requestString = $request->get('q');

        $entities = $centreRepo->findEntitiesByString($requestString);

        if(!$entities) {
            $result['entities']['error'] = "Aucun centre trouvé";
        } else {
            $result['entities'] = $this->getRealEntities($entities);
        }
        return new Response(json_encode($result));
        
    
    }
  
   

    
    

    public function getRealEntities($centre){
        foreach ($centre as $r){
            $realEntities[$r->getId()] = [$r->getNomCentre(),$r->getAdresse(), $r->getServices(),$r->getNumTel(),$r->getEMail()];
        }
        return $realEntities;
    }

}
