<?php


namespace App\Controller;
use App\Entity\Produit;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/cart", name="cart_")
 */

class CartController extends AbstractController
{
    /**
     * @Route("/", name="index")
     */
    public function index(SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;

        foreach($panier as $id => $quantite){
            $produit = $produitRepository->find($id);
            $dataPanier[] = [
                "produit" => $produit,
                "quantite" => $quantite
            ];
            $total += $produit->getPrixproduit() * $quantite;
        }

        return $this->render('cart/index.html.twig', compact("dataPanier", "total"));
    }

    /**
     * @Route("/add/{id}", name="add")
     */
    public function add(Produit $produit, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $produit->getId();

        if(!empty($panier[$id])){
            $panier[$id]++;
        }else{
            $panier[$id] = 1;
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove(Produit $produit, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $produit->getId();

        if(!empty($panier[$id])){
            if($panier[$id] > 1){
                $panier[$id]--;
            }else{
                unset($panier[$id]);
            }
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function delete(Produit $produit, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $produit->getId();

        if(!empty($panier[$id])){
            unset($panier[$id]);
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/delete", name="delete_all")
     */
    public function deleteAll(SessionInterface $session)
    {
        $session->remove("panier");

        return $this->redirectToRoute("cart_index");
    }

}
