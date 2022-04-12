using System;
using System.Collections.Generic;

namespace subsrt
{

	public static class Alghoritms
	{
		public static List<int> NaiveSearch(string text, string pattern)
		{
			var count = 0;
			var entries = new List<int>();
			for (int i = 0; i <= text.Length - pattern.Length; i++)
			{
				bool correct = true;
				for (int j = 0; j < pattern.Length && correct; j++)
				{
					count++;
					if (text[i + j] != pattern[j])
						correct = false;
				}
				if (correct)
					entries.Add(i);
			}
			Console.WriteLine("число сравнений" + count);
			return entries;
		}

		static int[] PrefixFunction(string pattern)
		{
			int[] prefix = new int[pattern.Length];

			int lastPrefix = prefix[0] = 0;
			for (int i = 1; i < pattern.Length; i++)
			{
				while (lastPrefix > 0 && pattern[lastPrefix] != pattern[i])
					lastPrefix = prefix[lastPrefix - 1];

				if (pattern[lastPrefix] == pattern[i])
					lastPrefix++;

				prefix[i] = lastPrefix;
			}

			return prefix;
		}

		public static List<int> KMPSearch1(String text, String pattern)

		{
			var entries = new List<int>();

			int[] prefixFunc = PrefixFunction(pattern);

			var count = 0;
			int[] prefix = PrefixFunction(pattern);
			int last_prefix = 0;
			for (int i = 0; i <= text.Length - pattern.Length; i++)
			{
				while (last_prefix < pattern.Length && last_prefix > 0 && pattern[last_prefix] != text[i])
				{
					count++;
					last_prefix = prefix[last_prefix - 1];
				}

				if (pattern[last_prefix] == text[i])
				{
					last_prefix++;
					count++;
				}

				if (last_prefix == pattern.Length)
				{
					entries.Add(i + 1 - pattern.Length);
					last_prefix = 0;
				}


			}
			Console.WriteLine("число сравнений" + count);
			return entries;
		}

		public static List<int> KMPSearch(String text, String pattern)
		{
			var entries = new List<int>();

			int[] prefixFunc = PrefixFunction(pattern);

			int i = 0;
			int j = 0;
			var count = 0;
			while (i <= text.Length - pattern.Length+j)
			{
				count++;
				if (pattern[j] == text[i])
				{
					//count += 1;
					j++;
					i++;
				}

				if (j == pattern.Length)
				{
					entries.Add(i - j);
					j = prefixFunc[j - 1];
				}
				else if (i < text.Length && pattern[j] != text[i])
				{
					//count += 1;
					if (j != 0)
					{
						j = prefixFunc[j - 1];
					}
					else
					{
						i = i + 1;
					}
				}
			}

			Console.WriteLine("число сравнений" + count);
			return entries;
		}
	}

	// public static int Hash(string x)
	// {
	// 	int p = 31;
	// 	int hash = 0;
	// 	for (int i = 0; i < x.Length; i++)
	// 	{
	// 		hash += (int)Math.Pow(p, x.Length - 1 - i) * x[i];
	// 	}
	// 	return hash;
	// }
	//
	// public static List<int> RabinaKarpaSearch(string text, string pattern)
	// {
	// 	var entries = new List<int>();
	// 	if (pattern.Length > text.Length) return new List<int>();
	// 	int patternhash = Hash(pattern);
	// 	int textHash = Hash(text.Substring(0, pattern.Length));
	// 	bool flag;
	// 	int j;
	// 	for (int i = 0; i < text.Length - pattern.Length + 1; i++)
	// 	{
	// 		if (patternhash == textHash)
	// 		{
	// 			flag = true;
	// 			j = 0;
	// 			while (flag && j < pattern.Length)
	// 			{
	// 				if (pattern[j] != text[i + j]) flag = false;
	// 				j++;
	// 			}
	// 			if (flag)
	// 				entries.Add(i);
	// 		}
	// 		else
	// 			textHash = (textHash - (int)Math.Pow(31, pattern.Length - 1) * (int)(text[i])) * 31 + (int)(text[i + pattern.Length]);
	// 	}
	// 	Console.WriteLine("hash is over");
	// 	return entries;
	// }

	// public static List<int> RKSearch(String text, String pattern)
	// {
	// 	var entries = new List<int>();
	// 	int d = 256;
	// 	int q = 101;
	// 	int i, j;
	// 	int patternHash = 0; 
	// 	int textHash = 0;
	//
	// 	checked
	// 	{
	// 		int h = 1;
	// 		for (i = 0; i < pattern.Length - 1; i++)
	// 			h = (h * d) % q;
	//
	// 		for (i = 0; i < pattern.Length; i++)
	// 		{
	// 			patternHash = (d * patternHash % q + pattern[i]) % q;
	// 			textHash = (d * textHash % q + text[i]) % q;
	// 		}
	//
	// 		for (i = 0; i <= text.Length - pattern.Length; i++)
	// 		{
	// 			if (patternHash == textHash)
	// 			{
	// 				for (j = 0; j < pattern.Length; j++)
	// 				{
	// 					if (text[i + j] != pattern[j])
	// 						break;
	// 				}
	//
	// 				if (j == pattern.Length)
	// 					entries.Add(i);
	// 			}
	//
	// 			if (i < text.Length - pattern.Length)
	// 			{
	// 				textHash = (d * (textHash - text[i] * h) % q + text[i + pattern.Length]) % q;
	//
	// 				if (textHash < 0)
	// 					textHash += q;
	//
	// 				Console.WriteLine(textHash);
	// 			}
	// 		}
	//
	// 		Console.WriteLine("hash is over");
	// 		return entries;
	// 	}
	// }


	class Program
	{
		static void Main(string[] args)
		{
			// var text = "I think it might be easier to create a routine that prints all the necessary text on-screen and rewrite the entire text each time the user presses a magic key, highlighting as you go.";
			// var pattern = "to create a routine that prints all the necessary text on-screen and rewrite the entire text";
			//var text = "ABAABAABCABA";
			var text = "AAAAAA";
			var pattern = "AAAB";
			//var pattern1 = "AAAB";
			//ApplyAlghoritm(Alghoritms.NaiveSearch, text1, pattern1);
			ApplyAlghoritm(Alghoritms.KMPSearch, text, pattern);
			//ApplyAlghoritm(Alghoritms.RabinaKarpaSearch, text1, pattern1);
			// ApplyAlghoritm(Alghoritms.RKSearch, text, pattern);
		}

		public static void ApplyAlghoritm(Func<string, string, List<int>> alghoritm, string text, string pattern)
		{
			var result = alghoritm(text, pattern);
			if (result.Count > 0)
			{
				result.ForEach(Console.WriteLine);
				Console.WriteLine("___________________________________");
			}
		}
	}
}
