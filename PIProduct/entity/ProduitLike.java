
package gestionproduit.entity;

/**
 *
 * @author bouyo
 */
public class ProduitLike {
    
    private int id;
    private int idUser;
    private int idProduit;

    public ProduitLike(int id, int idUser, int idProduit) {
        this.id = id;
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public ProduitLike() {
    }

    public ProduitLike(int idUser, int idProduit) {
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    
    
    
}
