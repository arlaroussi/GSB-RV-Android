package gsb.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import models.Visiteur;

public class VisiteurDAO {

        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "eleves.db";

        private static final String TABLE_VISITEUR = "visiteur";
        private static final String COL_ID = "ID";
        private static final int NUM_COL_ID = 0;
        private static final String COL_NOM = "nom";
        private static final int NUM_COL_NOM = 1;
        private static final String COL_PRENOM = "prenom";
        private static final int NUM_COL_PRENOM = 2;
        private static final String COL_LOGIN = "login";
        private static final int NUM_COL_LOGIN = 3;
        private static final String COL_PASSWORD = "password";
        private static final int NUM_COL_PASSWORD = 4;

        //Objet de la classe SQLIteDatabase
        private SQLiteDatabase bdd;

        //Objet de la classe SQLiteOpenHelper
        private BDDSQLite BDGSB;

        //Méthode pour la création de la BDD
        public VisiteurDAO(Context context){
            BDGSB = new BDDSQLite(context, NOM_BDD, null, VERSION_BDD);
        }
         //Ouverture de la BDD en écriture
        public SQLiteDatabase open(){
            return bdd = BDGSB.getWritableDatabase();
        }
        //Fermeture de la BDD
        public void close(){
            bdd.close();
        }
       //Accesseur
        public SQLiteDatabase getBDD(){
            return bdd;
        }
        public long insertVisiteur(Visiteur visiteur){
            //Création d'un ContentValues
            ContentValues values = new ContentValues();
            //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
            values.put(COL_NOM, visiteur.getNom());
            values.put(COL_PRENOM, visiteur.getPrenom());
            values.put(COL_LOGIN, visiteur.getLogin());
            values.put(COL_PASSWORD, visiteur.getPassword());
            //on insère l'objet dans la BDD via le ContentValues
            return bdd.insert(TABLE_VISITEUR, null, values);
        }

        public int updateVisiteur(int id, Visiteur visiteur){

            ContentValues values = new ContentValues();
            values.put(COL_NOM, visiteur.getNom());
            values.put(COL_PRENOM, visiteur.getPrenom());
            values.put(COL_LOGIN, visiteur.getLogin());
            values.put(COL_PASSWORD, visiteur.getPassword());
            return bdd.update(TABLE_VISITEUR, values, COL_ID + " = " +id, null);
        }

        public int removeVisiteurWithID(String id){
            //Suppression d'un visiteur
            return bdd.delete(TABLE_VISITEUR, COL_ID + " = " +id, null);
        }

        public Visiteur getVisiteurWithNom(String nom){
            //Récupère dans un Cursor les valeurs correspondant à un visiteur
            Cursor c = bdd.query(TABLE_VISITEUR, new String[] {COL_ID, COL_NOM, COL_PRENOM, COL_LOGIN}, COL_NOM + " LIKE \"" + nom +"%\"", null, null, null, null);
            return cursorToVisiteur(c);
        }

        public Visiteur getVisiteurWithLogin(String login){
           //Récupère dans un Cursor les valeurs correspondant à un visiteur
        Cursor c = bdd.query(TABLE_VISITEUR, new String[] {COL_ID, COL_NOM, COL_PRENOM, COL_LOGIN}, COL_LOGIN + " LIKE \"" + login +"\"", null, null, null, null);
        return cursorToVisiteur(c);
       }

        //Cette méthode permet de convertir un cursor en un visiteur

        public Visiteur cursorToVisiteur(Cursor c){
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            if (c.getCount() == 0)
                return null;
            //Sinon on se place sur le premier élément
            c.moveToFirst();
            //On créé un visiteur
            Visiteur visiteur = new Visiteur();
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            visiteur.setId(c.getString(NUM_COL_ID));
            visiteur.setNom(c.getString(NUM_COL_NOM));
            visiteur.setPrenom(c.getString(NUM_COL_PRENOM));
            visiteur.setLogin(c.getString(NUM_COL_LOGIN));
            //On ferme le cursor
            c.close();
            //On retourne le visiteur
            return visiteur;
        }
    }
