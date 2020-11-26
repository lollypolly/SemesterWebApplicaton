document.addEventListener('DOMContentLoaded', function () {
        let password = document.getElementById("inputPassword");
        let button = document.getElementById("asseptSignUp");


        function checkPassword() {
            let check = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
            if (!password.value.match(check)) {
                password.setCustomValidity("Неправильный формат пароля. " +
                    "Пароль должен состоять от 6 до 20 латинских символов, содержать хотя бы одну цифру и одну заглавную букву.");
            } else {
                password.setCustomValidity('');
            }
        }


        password.addEventListener('change', checkPassword, false);

        button.addEventListener('keyup', checkPassword, false);


    }
    , false);