

$(function (){

    $('#user_table').bootstrapTable({
        url: '/user/list',
        columns: [{
            field: 'id',
            title: '用户ID'
        }, {
            field: 'username',
            title: '用户名'
        }, {
            field: 'password',
            title: '用户密码'
        }]
    });
});