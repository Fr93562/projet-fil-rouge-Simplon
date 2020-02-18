import { TypeUser} from './typeUser';
import { Language } from './language';

/**
 * corresponds aux joueurs et administrateurs
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