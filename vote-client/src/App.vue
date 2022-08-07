<template>
  <div id="app">
<!--    <img alt="Vue logo" src="./assets/logo.png">-->
    <vote-center></vote-center>
  </div>
</template>

<script>
import VoteCenter from "./components/VoteCenter";
export default {
  name: 'App',
  components: {
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
