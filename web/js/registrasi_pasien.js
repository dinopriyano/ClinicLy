$(document).ready(function () {
        var userID = sessionStorage.getItem('userID');
        var email,name,pass,phone,numIdCard,address,birthDate,bloodType,gender;
        function getVal() {
            name = $('#name').val();
            email = $('#email').val();
            pass = $('#pass').val();
            phone = $('#phone').val();
            numIdCard = $('#numIdCard').val();
            address = $('#address').val();
            birthDate = new Date($('#birthDate').val()).getTime();
            bloodType = $("#bloodType").children("option:selected").val();
            gender = $('input[name="gender"]:checked').val();
        }
    
        $('#btnSignUp').click(function () {
            getVal();
            $.post('/ClinicLy/Login', {
                page: 'insert',
                email: email,
                password: pass,
                nama: name,
                noHp: phone,
                noKtp: numIdCard,
                alamat: address,
                tglLahir: bloodType,
                jenisKelamin: gender,
                golDarah: bloodType,
                createdUserId: userID
            }, function (data, status) {
                alert(data);
                if(data === "Data Tersimpan"){
                    document.location.href = '/ClinicLy/pasien/pasien.html';
                }
            });
        });
    
    });