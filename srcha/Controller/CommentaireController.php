<?php


namespace App\Controller;


use App\Entity\Commentaire;
use App\Entity\Conseils;
use App\Repository\CommentaireRepository;
use App\Repository\ConseilsRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class CommentaireController extends AbstractController
{
    /**
     * @var ConseilsRepository
     */
    private $conseilsRepository;
    /**
     * @var UserRepository
     */
    private $userRepository;
    /**
     * @var EntityManagerInterface
     */
    private $entityManager;
    /**
     * @var commentaireRepository
     */
    private $commentaireRepository;

    public function __construct(ConseilsRepository $conseilsRepository, UserRepository $userRepository, EntityManagerInterface $entityManager, CommentaireRepository $commentaireRepository)
    {

        $this->conseilsRepository = $conseilsRepository;
        $this->userRepository = $userRepository;
        $this->entityManager = $entityManager;
        $this->commentaireRepository = $commentaireRepository;
    }

    /**
     * @Route("/commentaire", name="commentaire_index", methods={"GET"})
     */
    public function commentaire(){

        $conseil = $this->conseilsRepository->findAll();

        return $this->render('conseils/commentaire.html.twig',[
            'conseils' => $conseil,

        ]);

    }

    /**
     * @Route("/commentaire/{id}", name="single_comment", methods={"GET"})
     */

    public function singleComment(Conseils $id){
        $conseil = $this->conseilsRepository->find($id->getIdArticle());
        $user = $this->userRepository->find(2);
        $commentaire = $this->commentaireRepository->findAll();

        return $this->render('conseils/singlePost.html.twig',[
            'conseil' => $conseil,
            'commentaires' => $commentaire,
            'user' =>  $user->getNom() .' '. $user->getPrenom()
        ]);
    }

    /**
     * @param Request $request
     * @return JsonResponse
     * @Route("/commentaire/create", name="create_commentaire", options={"expose" = true})
     */
    public function createCommentaire(Request $request){
        $comment = $request->request->get('comment');
        $postId = $request->request->get('postId');
        $newComment = new Commentaire();
        $newComment->setContent($comment);
        $user = $this->userRepository->find(1);
        $newComment->setIduser($user->getid_client());
        $article = $this->conseilsRepository->find((int)$postId);
        $newComment->setIdarticle($article);
        $this->entityManager->persist($newComment);
        $this->entityManager->flush();






        return $this->json([
            'success' => true,
            'comment' => $newComment,
            'users'  => $user->getNom() .' '. $user->getPrenom()

        ]);
    }

    /**
     * @Route("/pdf/{id}", name="comment_pdf", methods={"GET"})
     */
    public function getPdf(Conseils $id){
        $conseil = $this->conseilsRepository->find($id->getIdArticle());

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('default/mypdf.html.twig', [
            'conseil' => $conseil->getDescription()
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
    }







}