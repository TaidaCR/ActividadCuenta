CategoriaIngreso = validador.pideEnum("Elige categoria", "Debes elegir una categoria de las indicadas", CategoriaIngreso);



---------------------

...

Elige categoria (EMPLEO, PASIVOS, NEGOCIOS, PENSIONES, OTROS): lolailo
Debes elegir una categoria de las indicadas
Elige categoria (EMPLEO, PASIVOS, NEGOCIOS, PENSIONES, OTROS):


---------------------

        boolean enumIncorrecto = true;
        Enum<?> valor;

        while (valorIncorrecto) {
            // con mensaje y el enum que nos han informado montamos el nuevo mensaje
            // añadir los valores del enum al mensaje, con parentesis, comas y espacio

            System.out.print(mensajeModificado);
            
            valor = scanner.nextLine();
            // Comprobar si valor esta entre los valores permitidos del enum


            valorIncorrecto = false;
            scanner.nextLine();
        }

        return valor;