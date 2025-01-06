document.getElementById('trainForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = {
        trainName: document.getElementById('trainName').value,
        noOfCoaches: parseInt(document.getElementById('noOfCoaches').value),
        startStation: document.getElementById('startStation').value,
        endStation: document.getElementById('endStation').value,
        trainType: document.getElementById('trainType').value,
        avgSpeed: parseFloat(document.getElementById('avgSpeed').value)
    };

    fetch('/train/register', {
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