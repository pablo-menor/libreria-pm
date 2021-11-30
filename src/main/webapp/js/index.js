

const tabla = document.querySelector('#tabla-libros');
const sendBTN = document.querySelector('.send');
sendBTN.style.pointerEvents= 'none';
sendBTN.style.opacity= '0.7';

tabla.addEventListener('click', (e)=> {
	
	if(e.target.tagName === 'INPUT'){
		const sendBTN = document.querySelector('.send');
		sendBTN.style.pointerEvents= 'all';
	}
});