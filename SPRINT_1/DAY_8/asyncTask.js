async function runTasksSequentially(tasks) {
  const results = [];

  for (const task of tasks) {
    try {
      const result = await task(); 
      results.push(result);        
    } catch (error) {
      console.error("Task failed:", error);
    }
  }

  return results;
}
const tasks = [
  () => Promise.resolve("Task 1 done"),
  () => Promise.reject("Task 2 error"),
  () => Promise.resolve("Task 3 done")
];

runTasksSequentially(tasks).then(results => {
  console.log("Successful results:", results);
});
