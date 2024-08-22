
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
	$.getJSON('/reservas', function(data) { 
		$('#guestTableBody').empty(); 
		data.forEach(guest => { 
			$('#guestTableBody').append( 
				`<tr>  
					<td>${guest.id}</td>  
					<td>${guest.hospede.name}</td>  
					<td>${guest.quarto.numero}</td>  
					<td>${guest.checkin}</td>  
					<td>${guest.checkout}</td>
					<td>  
						<button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.hospede.id}', '${guest.quarto.id}', '${guest.checkin}', '${guest.checkout}')">Edit</button>  
						<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>  
					</td>  
				</tr>` 
			); 
		}); 
	}); 
} 
function showAddGuestForm() { 
	$('#formTitle').text('Add reservas'); 
	$('#guestId').val(''); 
	$('#guestHospede').val(''); 
	$('#guestQuarto').val(''); 
	$('#guestCheckIn').val(''); 
	$('#guestCheckOut').val('');
	$('#guestFormModal').show(); 
} 
function showEditGuestForm(id, hospede, quarto, checkin, checkout) { 
	$('#formTitle').text('Edit Reserva'); 
	$('#guestId').val(id); 
	$('#guestHospede').val(hospede); 
	$('#guestQuarto').val(quarto); 
	$('#guestCheckIn').val(checkin); 
	$('#guestCheckOut').val(checkout);
	$('#guestFormModal').show(); 
} 
function closeGuestForm() { 
	$('#guestFormModal').hide(); 
} 
 
function addGuest() { 
	const guest = { 
		hospede: {id:$('#guestHospede').val()}, 
		quarto: {id:$('#guestQuarto').val()}, 
		checkin: $('#guestCheckIn').val(),
		checkout: $('#guestCheckOut').val() 
	}; 
	$.ajax({ 
		url: '/reservas', 
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
		hospede: {id:$('#guestHospede').val()}, 
		quarto: {id:$('#guestQuarto').val()}, 
		checkin: $('#guestCheckIn').val(),
		checkout: $('#guestCheckOut').val() 
	}; 
	$.ajax({ 
		url: `/reservas/${id}`, 
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
			url: `/reservas/${id}`, 
			type: 'DELETE', 
			success: function() { 
				loadGuests(); 
			} 
		}); 
	} 
} 