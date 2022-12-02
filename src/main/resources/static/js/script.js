function validateNumber(number) {
    if(number == ""){
        alert("Number is missing");
        return false;
    }
    const url = 'http://localhost:8099/api/numbers/validation/' + number;
    fetch(url)
    .then(data => data.json())
    .then((json) => {

        var result = "";
        if(json.valid){
            result = number + " is correct ";
            if(json.correctedNumber != ""){
                result = number + " corrected with " + json.correctedNumber;
            }
        } else {
            result = number + " is NOT correct "
        }

        document.getElementById('numberResult').innerHTML = result;
    })
}


