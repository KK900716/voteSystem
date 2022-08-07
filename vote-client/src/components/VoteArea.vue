<template>
    <el-container class="vote-area" >
      <template>
        <el-dialog
            title="投票"
            :visible.sync="voteDialogVisable"
            width="30%"
            destroy-on-close
            @close="handleClose()">
          <template>
            <div>{{this.name}}</div>
            <el-input-number  v-model="num" @change="handleChange" :min="1" :max="10" label="投票分数"></el-input-number>
          </template>
          <span slot="footer" class="dialog-footer">
                        <el-button @click="voteDialogVisable = false">取 消</el-button>
                        <el-button type="primary" @click="sendToWebBack">确 定</el-button>
                    </span>
        </el-dialog>

      </template>
        <el-table
                :data="voteNames"
                stripe
                class="el-table-demo"
                style="width: 100%;">
            <el-table-column
                    prop="name"
                    class="el-table-demo-item"
                    label="姓名"
                    width="400">
            </el-table-column>

            <el-table-column
                    class="el-table-demo-item"

                    label="投票"
                    width="400"

            >
                <template slot-scope="scope" >
                    <el-button type="success"  @click="voteDialog(scope.row.name)">投票</el-button>
                </template>
            </el-table-column>
        </el-table>

    </el-container>

</template>

<script>
// import axios from "axios";
import axios from "axios";

export default {
        name: "VoteArea",
        data() {
            return{
                voteNames:[{
                  name:"zhangsan"
                }],
                name:'',
                num:1,
                activeIndex: '1',
                activeIndex2: '1',
                voteDialogVisable:false,
            }
        },
        created() {
            this.getVoteName()
        },
        methods:{

            sendToWebBack(){
                let url_data = this.$qs.stringify({
                    name:this.name,
                    score:this.num
                })
                axios.post(`/voteMessage`,
                    url_data
                ).then(
                    (res)=> {
                      //   console.log(res.data.valueOf())
                      // let isTrue = .valueOf()
                        if(res.data == true){
                            console.log(res.data)
                            this.voteDialogVisable=false
                            this.$message({
                              message: '投票成功！',
                              type: 'success'
                            });
                            this.getVoteName()
                        }
                        else {
                            alert("投票失败");
                        }

                    }
                )
            },
            voteDialog(value){
              console.log("点击了："+value)
                this.name=value
                this.voteDialogVisable=true
            },
            handleClose() {
                this.voteDialogVisable=false
            },
            handleChange(){
                // this.sendToWebBack()
            },
            getVoteName(){
                axios.get(`/getVoteNames`

                ).then(
                    (res)=> {
                        this.voteNames=res.data

                        console.log(res.data)
                        // if(res.data == true){
                        //     console.log(res.data())
                        // }
                        // else {
                        // }

                    }
                )
            }

        }
    }
</script>

<style scoped>
.vote-area{
    background-color: #545c64;
    display: flex;
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
}
    .el-table-demo{
        /*width: 100%;*/
        /*display: flex;*/
        /*align-items: center; !* 垂直居中 *!*/
        /*justify-content: center; !* 水平居中 *!*/
    }
.el-table-demo-item{
    width: 500px;
    display: flex;
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
}
</style>