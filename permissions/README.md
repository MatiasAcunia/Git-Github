# Shell, permisos

0-iam_betty: Cambia la sesión al usuario betty; comando de exactamente ocho caracteres.
1-who_am_i: Imprime el nombre del usuario efectivo actual.
2-groups: Imprime los grupos del usuario actual.
3-new_owner: Cambia el propietario de hello a betty.
4-empty: Crea un archivo vacío llamado hello.
5-execute: Agrega permiso de ejecución al propietario de hello.
6-multiple_permissions: Agrega ejecución al propietario y grupo y lectura a otros para hello.
7-everybody: Agrega ejecución a propietario, grupo y otros para hello, sin usar comas.
8-James_Bond: Establece permisos de hello: propietario 0, grupo 0, otros 7, sin comas.
9-John_Doe: Establece permisos de hello en rwxr-x-wx, sin comas.
10-mirror_permissions: Copia a hello los permisos de olleh, cualquiera sea el modo.
11-directories_permissions: Agrega ejecución a todos los subdirectorios sin alterar archivos regulares.
12-directory_permissions: Crea my_dir en el directorio actual con permisos 751.
13-change_group: Cambia el grupo de hello a school.
14-change_owner_and_group: Cambia propietario y grupo de todos los archivos y directorios actuales a vincent:staff.
15-symbolic_link_permissions: Cambia el propietario y grupo del enlace simbólico _hello, no de su destino.
16-if_only: Cambia propietario de hello a vincent solamente cuando el propietario actual es guillaume.
