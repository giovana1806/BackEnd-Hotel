
$(document).ready(function() {
	loadGuests();
	$('#guestForm').submit(function(event) {
		event.preventDefault();
		const id = $('#guestId').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});
function loadGuests() {
	$.getJSON('/quartos', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>  
					<td>${guest.id}</td>  
					<td>${guest.descricao}</td>  
					<td>${guest.numero}</td>  
					<td>${guest.status}</td> 
					<td>  
						<button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.descricao}', '${guest.numero}', '${guest.status}')">Edit</button>  
						<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>  
					</td>  
				</tr>`
			);
		});
	});
}
function showAddGuestForm() {
	$('#formTitle').text('Add quartos');
	$('#guestId').val('');
	$('#guestDescricao').val('');
	$('#guestNumero').val('');
	$('#guestStatus').val('');
	$('#guestFormModal').show();
}
function showEditGuestForm(id, descricao, numero, status) {
	$('#formTitle').text('Edit Quarto');
	$('#guestId').val(id);
	$('#guestDescricao').val(descricao);
	$('#guestNumero').val(numero);
	$('#guestStatus').val(status);
	$('#guestFormModal').show();
}
function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		descricao: $('#guestDescricao').val(),
		numero: $('#guestNumero').val(),
		status: $('#guestStatus').val()
	};
	$.ajax({
		url: '/quartos',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}
function updateGuest(id) {
	const guest = {
		descricao: $('#guestDescricao').val(),
		numero: $('#guestNumero').val(),
		status: $('#guestStatus').val()
	};
	$.ajax({
		url: `/quartos/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}
function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/quartos/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			}
		});
	}
}