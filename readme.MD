#EXAMEN 3 POO

Integrantes:
- García Tinajero Lizeth
- Aguilar GArnica ALfredo
- López Martinez Eduardo

*Resumen del problema:*

Una fintech está en proceso de convertirse en un banco y necesita un sistema para gestionar la empresa. El sistema debe cumplir con los siguientes requisitos:

*Sucursales y Gerentes:*
- El banco comenzará con dos sucursales: "Madero" y "Acueducto", cada una con un único gerente.
- El gerente de sucursal tiene acceso a todas las operaciones del sistema y ya debe estar registrado por defecto.

*Roles:*
1. *Clientes:* Se registran con ejecutivos de cuenta y se les asigna una tarjeta de débito.
2. *Empleados:* Gerentes de sucursal, ejecutivos de cuenta y capturistas.
3. *Inversionistas:* Registrados y gestionados por el gerente.

*Clientes:*
- Se registran con datos personales y se les asigna una tarjeta de débito.
- Pueden tener hasta 3 tarjetas de crédito (simplicity, platino y oro) según su saldo.
- Las tarjetas de crédito tienen límites y se pueden solicitar con aprobación de ejecutivos de cuenta y gerente.

*Empleados:*
- Todos tienen datos personales y están asociados a una sucursal.
- Los ejecutivos de cuenta pueden realizar operaciones con clientes y autorizar solicitudes de tarjetas.
- Los capturistas pueden realizar operaciones solo con ejecutivos de cuenta.

*Inversionistas:*
- Son gestionados por el gerente y pueden proveer fondos al banco.
- Sus movimientos son visibles solo para el gerente y están protegidos por contraseña.

*Tarjetas:*
- Cada cliente tiene una tarjeta de débito y hasta 3 tarjetas de crédito.
- Las solicitudes de tarjetas de crédito deben ser autorizadas por ejecutivos de cuenta y el gerente.

*Notas:*
- Cada usuario tiene su propia contraseña.
- Los movimientos son únicos por sucursal.
- Se deben realizar todas las validaciones necesarias.
- El proyecto debe ser desarrollado por equipos de 3 personas y subido a una rama específica en el repositorio.