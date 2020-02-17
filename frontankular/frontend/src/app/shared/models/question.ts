import { Categorie } from './categorie';
import { Ressource } from './ressource';


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