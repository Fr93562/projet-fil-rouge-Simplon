import { Categorie } from './categorie';
import { Ressource } from './ressource';

/**
 * objet question, possède deux relations:
 * n - 1 avec l'objet categorie
 * relation unidirectionnelle avec langage (coté langage)
 */
export class Question {
    id: number;
    level: number;
    question: string;
    answer: string;
    choice1: string;
    choice2: string;
    choice3: string;
    categorie: Categorie;
    ressource: Ressource;
}