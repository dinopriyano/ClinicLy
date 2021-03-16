$(document).ready(function() {
    
    if (sessionStorage.getItem('isLogin') === null) {
        alert('You are not login yet! Login first!');
        document.location.href = '/ClinicLy/login.html';
    }
    
    // $("#tableanggota tbody").on('click','#btnDel',function()
    // {
    //     var row=$(this).closest('tr');
    //     var no=row.find("td:eq(0)").text();
    //     var nama=row.find("td:eq(1)").text();
    //     page="delete";
        
    //     if(confirm("Anda yakin data : ' " +no+" - "+nama+" ' akan dihapus ?"))
    //     {
    //         $.post("/Koperasi/AnggotaController",
    //         {
    //             page:page,
    //             no:no
    //         },
    //         function(data,status)
    //         {
    //             alert(data);
    //             location.reload();
    //         });
    //     }
    // });

    // $("#tableanggota tbody").on('click','#btnEdit',function()
    // {
    //     $("#myModal").show();
    //     $("#titleAdd").hide();
    //     $("#titleEdit").show();
    //     $("#no").prop('disabled',true);
    //     page='show';
        
    //     var row=$(this).closest('tr');
    //     var no=row.find("td:eq(0)").text();
        
    //     $.post("/Koperasi/AnggotaController",
    //     {
    //         page:page,
    //         no:no
    //     },
    //     function(data,status)
    //     {
    //         $("#no").val(data.noanggota);
    //         $("#nama").val(data.nama);
    //         $("#gender").val(data.gender);
    //         $("#tmpLahir").val(data.tmplahir);
    //         $("#tglLahir").val(data.tgllahir);
    //         $("#alamat").val(data.alamat);
    //         $("#telepon").val(data.telepon);
    //     });
    //     page="update";
    // });
    
    $.ajax ({
        url: "/ClinicLy/Pasien",
        method: "GET",
        dataType: "json",
        success:
                function(data)
                {   
                    $("#tablePasien").dataTable({
                        serverside: true,
                        processing: true,
                        data: data,
                        sort: true,
                        searching: true,
                        paging: true,
                        columns: [
                            {'data': 'id_pasien', 'name':'id_pasien', 'type':'string'},
                            {'data': 'nama_pasien'},
                            {'data': 'email'},
                            {'data': 'no_hp', 'classname':'text-center'},
                            {'data': 'tgl_lahir'},
                            {'data': 'no_ktp'},
                            {'data': 'gol_darah'},
                            {'data': 'jenis_kelamin'},
                            {'data':null, 'className':'dt-center',
                                'mRender':function(o)
                                {
                                    return "<button class='btn btn-success btn-sm'><i class='fas fa-edit'></i> Edit</button> <button class='btn btn-danger btn-sm'><i class='fas fa-trash'></i> Hapus</button>";
                                }
                            }
                        ]
                    });
                }
            });
           
        }
        );