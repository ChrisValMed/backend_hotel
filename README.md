## 🚨 Problema urgente de la Cadena  _Marco Eden_

#### ✉️ De: Doña Laura (dueño de Hoteles A y B)
#### 📅 Asunto: Las notas están raras y nadie responde mis mails

---

### 🧩 Contexto

¡Hola equipo de tecnología!

Soy Laura, orgullosa dueña de la cadena hotelera compuesta por **Hotel A** y **Hotel B**.  
Estamos atravesando una situación crítica y necesito su ayuda ASAP.

---

### 🛑 Problema 1: ¡El sistema ni siquiera arranca!

Sí, eso mismo.

Nadie lo tocó pero el sistema **no arranca más**.  
Tira mensajes crípticos de error y no puedo ni ver los logs.
Claramente algo se rompió y no es por mi culpa.

---

### 📬 Problema 2: Quiero ver qué mails se mandaron

Hace poco implementamos un sistema para enviar **agradecimientos automáticos por mail** a los huéspedes que respondieron la encuesta.

Pedí enviar un mail personalizado para los huéspedes que completaron las encuestas con los siguientes IDs:

```
5, 13, 69, 203, 205
```

El ultimo email que tengo registro, fue enviado al huesped Ian Perez III, mira:
```
{
    "from": "Hotel B",
    "subject": "Thank you for your feedback!",
    "to": "andreacollins@wright-king.info",
    "message": "Thanks Ian Perez III for answering our survey. Kind regards, Hotel B!"
}
```

Y todavía estoy esperando ver **qué se mandó y a quién**.


---

### 📉 Problema 3: ¡Las notas del Hotel B están bajísimas!

Cada mes calculamos el **promedio de satisfacción de los huéspedes** usando los datos de encuestas.  
Pero no usamos cualquier promedio... usamos un **promedio ponderado**.

> 🧠 _"¿Qué es un promedio ponderado?"_

El promedio ponderado toma en cuenta que **no todas las opiniones pesan lo mismo**.  
Se calcula con la fórmula:

```
Σ (nota × peso) / Σ (peso)
```

Ejemplo:

| Huésped | Nota | Peso |
|--------|------|------|
| A      | 7    | 2    |
| B      | 10   | 1    |
| C      | 8    | 3    |

→ Promedio ponderado = `(7×2 + 10×1 + 8×3) / (2 + 1 + 3) = 48 / 6 = 8`

---

#### 🤔 ¿Y qué está fallando?

El administrador del **Hotel B** notó que las notas están **más bajas de lo habitual**, a pesar de recibir buenos comentarios.  
Sospechamos que hay un error en el cálculo del promedio, segun nuestro último reporte manual, el promedio ponderado del Hotel B es de **6.4** pero el de su sistema nos esta dando 0.9 puntos menos.

---


### 🆕 Problema 4: ¿Desde qué dominio nos están escribiendo?

A esta altura del partido, ya no confío ni en mis propios huéspedes.
Necesito saber desde qué dominio de email se están registrando.

> 📧 "_¿Qué es un dominio de email, Donña Laura?_”

Por ejemplo:	
+ juan.perez@gmail.com → dominio = gmail.com 
+ maria@empresa.com.ar → dominio = empresa.com.ar 
+ lucho@hotelb.org → dominio = hotelb.org

---

🎯 Lo que quiero ahora

Contar cuántos huéspedes hay por dominio de email.
Y si puede ser, devolver una lista ordenada por cantidad, de mayor a menor, así veo si Gmail está ganando o si la competencia nos está infiltrando.

Ejemplo de salida esperada:

```
[
  {
    "domain": "gmail.com",
    "count": 15
  },
  {
    "domain": "empresa.com.ar",
    "count": 12
  },
  {
    "domain": "empresa.com.ar",
    "count": 3
  },
  // ...
]
```


## 🛠️ ¿Qué tecnología usa el proyecto?

- Java 17+
- Spring Boot 3
- Repositorios JPA y Beans con DI
- Configuraciones vía `application.properties`
- Simulación de envíos de email (con logs y delays)

