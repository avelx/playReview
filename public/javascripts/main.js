function onPageLoad(){
    //alert("This is a test")
    // Cheap way to trace execution
    console.log("Hello world!");
    alert("Hello data");
    changeTest();
}

function changeTest() {
    document.getElementById("uniqueId").innerHTML = "Paragraph changed.";
}

addEventListener("load", (event) => { })

onload = (event) => { onPageLoad() }