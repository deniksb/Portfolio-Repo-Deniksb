
let moduleCheckboxes = ['module1-checkbox','module2-checkbox','module3-checkbox','module4-checkbox','module5-checkbox','module6-checkbox','module7-checkbox'];

//get checkbox values from localstorage or save them if they are not there
for(let i=0;i<moduleCheckboxes.length;i++){
  let boxId =  moduleCheckboxes[i];
  if(localStorage.getItem(moduleCheckboxes[i]) != undefined){

    document.getElementById(boxId).checked = (localStorage.getItem(boxId) == 'true');
  }
  else{
    localStorage.setItem(moduleCheckboxes[i],'false');
    document.getElementById(boxId).checked = false;
  }
}

//call function when checkbox is clicked
function saveCheckboxValue(ele){
    localStorage.setItem(ele.id,ele.checked);
    console.log(ele.checked);
}
