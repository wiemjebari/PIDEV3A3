<?php


namespace App\Controller;


use App\Entity\Commentaire;
use App\Entity\Conseils;
use App\Repository\ConseilsRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
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

    public function __construct(ConseilsRepository $conseilsRepository, UserRepository $userRepository, EntityManagerInterface $entityManager)
    {

        $this->conseilsRepository = $conseilsRepository;
        $this->userRepository = $userRepository;
        $this->entityManager = $entityManager;
    }

    /**
     * @Route("/commentaire", name="commentaire_index", methods={"GET"})
     */
    public function commentaire(){

        $conseil = $this->conseilsRepository->findAll();
        return $this->render('conseils/commentaire.html.twig',[
            'conseils' => $conseil
        ]);

    }

    /**
     * @Route("/commentaire/{id}", name="single_comment", methods={"GET"})
     */

    public function singleComment(Conseils $id){
        $conseil = $this->conseilsRepository->find($id->getIdArticle());

        return $this->render('conseils/singlePost.html.twig',[
            'conseil' => $conseil
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
//        $user = $this->userRepository->findBy(1);
//        $newComment->setIduser($user);
        $article = $this->conseilsRepository->find((int)$postId);
        $newComment->setIdarticle($article);
        $this->entityManager->persist($newComment);
        $this->entityManager->flush();





        return $this->json([
            'success' => true

        ]);
    }






}