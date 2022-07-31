<template>
  <div id="app">
<!--    <img alt="Vue logo" src="./assets/logo.png">-->
    <vote-center></vote-center>
<!--    <router-view></router-view>-->
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import VoteCenter from "./components/VoteCenter";
export default {
  name: 'App',
  components: {
    // HelloWorld,
    VoteCenter
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
    margin: -8px -8px auto;
  /*margin-top: 60px;*/
}
</style>
