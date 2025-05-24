function isPalindrome(str) {
  const cleaned = str.replace(/[^a-z0-9]/gi, '').toLowerCase();
  return cleaned === cleaned.split('').reverse().join('');
}

console.log(isPalindrome("madam"));
console.log(isPalindrome("racecar"));         
console.log(isPalindrome("hello"));           
console.log(isPalindrome("A man, a plan, a canal: Panama")); 
