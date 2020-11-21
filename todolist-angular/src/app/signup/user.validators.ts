import { AbstractControl, ValidationErrors } from '@angular/forms';

export class UserValidators{

    static nameValidation(control:AbstractControl):ValidationErrors|null{
        if((control.value as string).search("[0-9]+")!=-1){
            return {
                nameValidation:true
            };
        }
        return null;
    }

    static emailValidation(control:AbstractControl):ValidationErrors|null{

        if((control.value as string).search("[0-9]+")!=-1){
            return {
                surnameValidation:true
            };
        }
        return null;
    }
}