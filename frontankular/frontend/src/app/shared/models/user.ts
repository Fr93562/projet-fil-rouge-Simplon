import { TypeUser} from './typeUser';
import { Language } from './language';

/**
 * objet joueur, possède des relations avec plusieurs objets:
 * n - 1 avec typeUser
 * n - n avec langage
 */
export class User{
    id: number;
    dateInscription: Date;
    email: string;
    password: string;
    username: string;
    typeUser: TypeUser;
    langage: Language[];
    ranking: number;
}