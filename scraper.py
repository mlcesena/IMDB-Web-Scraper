from bs4 import BeautifulSoup
import requests
import datetime
import dateparser as dp
import re

def get_movie_info(URL):
    # Variables
    title = ''
    release =''
    genres_html = []
    genres = []
    directors_html = []
    directors = []
    plot = ''
    rating = ''
    stars_html = []
    stars = []
    poster = ''
    movie_info = []

    # Gets info for movie from IMDB
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, features="html.parser")

    # Gets title of movie
    title = soup.find('div', class_='title_wrapper').find('h1').text
    title = title.split()
    actual_title = ""
    for title_word in title:
        actual_title += title_word + " "
    title = actual_title.strip()

    plot = soup.find('div', class_="plot_summary")
    director = plot.find('div', class_="credit_summary_item").find('a')

    # Gets list of genres
    genres_html = (soup.find('div', class_='subtext').find_all('a'))

    # Adds html genres to the genre list
    for i in genres_html[:-1]:
        genres.append(i.text)

    # Gets and formats the release date
    release = genres_html[-1].text
    release = release.split('(')[0]
    release = dp.parse(release).strftime("%Y-%m-%d")

    # Gets director of movie
    directors_html = soup.find('div', class_="credit_summary_item").findAll('a')
    for i in directors_html:
        directors.append(i.text)

    # Gets the plot of movie
    plot = soup.find('div', class_="summary_text").text
    plot = plot.replace('\n', '')
    plot = plot.replace('                    ', '')

    # Gets rating of movie and converts to a numerical value
    rating = soup.find('div', class_='ratingValue').text
    rating = rating.split('/')[0]
    rating = rating.replace('\n', '')
    rating = float(rating)

    # Gets stars of movie
    stars_html = soup.find('div', class_='plot_summary').find_all('a')
    for i in stars_html[-4:-1]:
        stars.append(i.text)
    
    # Gets poster of the movie
    poster = soup.find('div', class_='poster').find('img')['src']
    
    """
    # Prints movie info
    print('---------------------------------------------------------------------------------------------------------------------')
    print(title)
    print(release)
    print(genres)
    print(directors)
    print(plot)
    print(rating)
    print(stars)
    print(poster)
    print('---------------------------------------------------------------------------------------------------------------------')
    """

    movie_info.append(title)
    movie_info.append(release)
    movie_info.append(genres)
    movie_info.append(directors)
    movie_info.append(plot)
    movie_info.append(rating)
    movie_info.append(stars)
    movie_info.append(poster)

    return movie_info

   


def getMovieList():
    top_movies = []
    movies_html = []

    page = requests.get('https://www.imdb.com/chart/top/?ref_=nv_mv_250')
    soup = BeautifulSoup(page.content, features="html.parser")

    """
    movies_html = soup.find('tbody', class_='lister-list').find_all('a')['href']
    for movie in movies_html:
        m = movie.text
        top_movies.append(m)
    
        if m[0].isalpha():
            top_movies.append(m)
        print(m)
    """
    
    for a in soup.find('tbody', class_='lister-list').find_all('a', href=True): 
        if a.text: 
            movies_html.append('https://www.imdb.com' + a['href'])

    for i in movies_html:
        if i not in top_movies:
            top_movies.append(i)
    
    return top_movies

# Prompts user for a movie link
print('Welcome to the IMDB Scraper!')
URLs = []
movie = ''
movie_info = []

top_movies = getMovieList()

"""
print('Enter the IMDB URL(s) of your movies. Enter "exit" to finish: ')
while(movie != 'exit'):
    movie = input()
    
    if movie == 'exit':
        break

    URLs.append(movie)
"""
# Calls method to get info about the movie(s)
for links in top_movies:
    movie_info.append(get_movie_info(links))


with open('application/top_movies.txt', 'w') as filewriter:
    for line in movie_info:
        for entry in line:
            filewriter.write('%s\n' % entry)

# Movies to test
# Shutter Island: https://www.imdb.com/title/tt1130884/?ref_=fn_al_tt_1
# Avengers Endgame: https://www.imdb.com/title/tt4154796/?ref_=nv_sr_srsg_0
# Intersteller: https://www.imdb.com/title/tt0816692/?ref_=nv_sr_srsg_3
