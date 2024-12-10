package sio;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Devoir3
{
    public static void main(String[] args)
    {
        int choix;
        Scanner scanner = new Scanner(System.in);
        do
        {
            do
            {
                System.out.println("1 - Exercice 1");
                System.out.println("2 - Exercice 2");
                System.out.println("0 - Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
            } while (choix > 2);

            switch (choix)
            {
                case 0:
                    System.out.println("Fin du programme");
                    break;
                case 1:
                    int[] paquet = new int[]{1,2,3,4,5,6,7,8,9,10};
                    int[] paquetJoueur1 = new int[5];
                    int[] paquetJoueur2 = new int[5];


                    melangerTableau(paquet);

                    dispatcherTableaux(paquetJoueur1, paquetJoueur2, paquet);

                    System.out.println("Paquet mélangé : " + Arrays.toString(paquet));
                    System.out.println("Paquet Joueur 1 : " + Arrays.toString(paquetJoueur1));
                    System.out.println("Paquet Joueur 2 : " + Arrays.toString(paquetJoueur2));

                    comparerCartes(paquetJoueur1, paquetJoueur2);
                    break;

                case 2:
                    String[][] genres = new String[5][2];
                    String[][] films = new String[5][4];
                    int[][] notes = new int[5][7];

                    remplirTableauxExo2(genres, films, notes);
                    int choixMenuExo2;
                    do {
                        do {
                            System.out.println("1 - Afficher informations");
                            System.out.println("2 - Noter un film");
                            System.out.println("0 - Menu principal");
                            System.out.print("Votre choix : ");
                            choixMenuExo2 = scanner.nextInt();
                        } while (choixMenuExo2 > 2);
                        switch (choixMenuExo2) {
                            case 0:
                                break;
                            case 1:
                                afficherInfos(genres, films, notes);
                                break;
                            case 2:
                                System.out.print("Numéro du film à noter : ");
                                int numFilm = scanner.nextInt();
                                System.out.print("Votre note (1-5) : ");
                                int note = scanner.nextInt();
                                noterFilm(note, numFilm, notes, films);
                                break;
                        }
                    }while (choixMenuExo2 != 0);
                    break;
            }
        }while (choix != 0);
    }

    public static void melangerTableau(int[] unTableau) {
        Random random = new Random();
        for (int i = 0; i < unTableau.length * 2; i++) {
            int idx1 = random.nextInt(unTableau.length);
            int idx2 = random.nextInt(unTableau.length);

            echanger(unTableau, idx1, idx2);
        }
    }

    public static void echanger(int[] tableau, int i, int j) {
        int temp = tableau[i];
        tableau[i] = tableau[j];
        tableau[j] = temp;
    }

    public static void dispatcherTableaux(int[] tabJoueur1, int[] tabJoueur2, int[] tabPaquet) {
        for (int i = 0; i < tabPaquet.length; i++) {
            if (i % 2 == 0) {
                tabJoueur1[i / 2] = tabPaquet[i];
            } else {
                tabJoueur2[i / 2] = tabPaquet[i];
            }
        }
    }


    public static void comparerCartes(int[] tabJoueur1, int[] tabJoueur2) {
        int scoreJoueur1 = 0;
        int scoreJoueur2 = 0;


        System.out.println("\nDéroulement de la partie :");
        for (int i = 0; i < tabJoueur1.length; i++) {
            if (tabJoueur1[i] > tabJoueur2[i]) {
                System.out.println("Pli " + (i + 1) + " : Joueur 1 gagne (" + tabJoueur1[i] + " > " + tabJoueur2[i] + ")");
                scoreJoueur1++;
            } else if (tabJoueur1[i] < tabJoueur2[i]) {

                System.out.println("Pli " + (i + 1) + " : Joueur 2 gagne (" + tabJoueur1[i] + " < " + tabJoueur2[i] + ")");
                scoreJoueur2++;
            } else {
                System.out.println("Pli " + (i + 1) + " : Égalité (" + tabJoueur1[i] + " = " + tabJoueur2[i] + ")");
            }
        }

        System.out.println("\nScore final :");
        System.out.println("Joueur 1 : " + scoreJoueur1 + " points");
        System.out.println("Joueur 2 : " + scoreJoueur2 + " points");

        if (scoreJoueur1 > scoreJoueur2) {
            System.out.println("Le vainqueur est le Joueur 1 !");
        } else if (scoreJoueur1 < scoreJoueur2) {
            System.out.println("Le vainqueur est le Joueur 2 !");
        } else {
            System.out.println("La partie se termine par une égalité !");
        }
    }

    // Exercice 2 - Gestion des films et des notes
    public static void afficherInfos(String[][] genres, String[][] films, int[][] notes)
    {
        System.out.println("Films et leurs genres :");
        for (int i = 0; i < films.length; i++) {
            System.out.println("Film : " + films[i][1] + " | Genre : " + genres[Integer.parseInt(films[i][2]) - 1][1]);
            System.out.print("Notes : ");
            for (int j = 0; j < 5; j++) {
                System.out.print(notes[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void noterFilm(int uneNote, int indice, int[][] desNotes, String[][] desFilms)
    {
        int prochaineCaseVide = trouverProchaineCaseVide(desNotes, indice);
        if (prochaineCaseVide != -1) {
            desNotes[indice][prochaineCaseVide] = uneNote;
            System.out.println("Note ajoutée au film : " + desFilms[indice][1]);
        } else {
            System.out.println("Le film a déjà 5 notes.");
        }
    }

    public static int trouverProchaineCaseVide(int[][] tableau, int indice)
    {
        for (int i = 0; i < tableau[indice].length; i++) {
            if (tableau[indice][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void remplirTableauxExo2(String[][] genres, String[][] films, int[][] notes)
    {
        genres[0] = new String[]{"1", "Genre A"};
        genres[1] = new String[]{"2", "Genre B"};
        genres[2] = new String[]{"3", "Genre C"};
        genres[3] = new String[]{"4", "Genre D"};
        genres[4] = new String[]{"5", "Genre E"};

        films[0] = new String[]{"1", "Film A", "1","21"};
        films[1] = new String[]{"2", "Film B", "2","19"};
        films[2] = new String[]{"3", "Film C", "3","24"};
        films[3] = new String[]{"4", "Film D", "4","20"};
        films[4] = new String[]{"5", "Film E", "5","14"};

        notes[0] = new int[]{5, 4, 5, 4, 3, 0, 0};
        notes[1] = new int[]{3, 4, 4, 5, 3, 0, 0};
        notes[2] = new int[]{5, 5, 5, 4, 5, 0, 0};
        notes[3] = new int[]{4, 4, 3, 4, 5, 0, 0};
        notes[4] = new int[]{3, 2, 3, 2, 4, 0, 0};
    }
}
