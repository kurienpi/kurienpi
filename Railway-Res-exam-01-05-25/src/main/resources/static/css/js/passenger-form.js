document.getElementById('passengerType').addEventListener('change', function() {
    const seniorCitizenField = document.getElementById('seniorCitizenField');
    const handicappedField = document.getElementById('handicappedField');

    seniorCitizenField.style.display = 'none';
    handicappedField.style.display = 'none';

    if (this.value === 'SENIOR_CITIZEN') {
        seniorCitizenField.style.display = 'block';
    } else if (this.value === 'PHYSICALLY_HANDICAPPED') {
        handicappedField.style.display = 'block';
    }
});

document.getElementById('passengerForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        mobile: document.getElementById('mobile').value,
        passengerType: document.getElementById('passengerType').value,
        seniorCitizenId: document.getElementById('seniorCitizenId').value,
        physicalHandicappedProof: document.getElementById('physicalHandicappedProof').value
    };

    fetch('/passenger/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error('Error:', error));
});