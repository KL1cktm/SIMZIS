#include <iostream>
#include <ctime>
#include <map>
#include <cmath>

using namespace std;

string generateRandomString(int length, string alphabet);

void createHistogram(string str);

void calculateAverageTime(int str_len, int literal_size);

void main() {
	setlocale(LC_ALL, "ru");
	string alphabet = "0123456789";
	int length;
	cout << "Ввебите длину пароля (100макс.): ";
	cin >> length;
	cout << endl;
	if (length > 100) {
		cout << "неверный ввод";
		return;
	}
	string random_str = generateRandomString(length, alphabet);
	cout << random_str;
	createHistogram(random_str);
	calculateAverageTime(length, alphabet.size());
	return;
}

string generateRandomString(int length, string alphabet) {
	string result;
	srand(time(0));
	for (int i = 0; i < length; i++) {
		result += alphabet[rand() % alphabet.length()];
	}
	return result;
}

void createHistogram(string str) {
	map<char, int> histogram;
	for (char c: str)
	{
		histogram[c]++;
	}
	cout << "\nЧастота вхождения символов в строку:\n";
	for (auto h: histogram)
	{
		cout << h.first << ": ";
		for (int i = 0; i < h.second; i++)
		{
			cout << "[]";
		}
		cout << " (" << h.second << ")" << endl;
	}
}

void calculateAverageTime(int str_len, int alphabet_size) {
	for (int i = 1; i <= str_len; i++)
	{
		long double combinationCounter = 0;
		for (int j = 0; j <= i; j++) {
			combinationCounter += pow(alphabet_size, j);
		}
		long double averageTime = combinationCounter / (2 * 1000000);
		cout << "Среднее время подбора пароля методом перебора длины " << i << " составило: " << averageTime << 'с' << std::endl;
	}
}