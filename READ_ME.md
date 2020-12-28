# ezPark
Welcome to ezPark! A simple Android application for easy parking spot reservations in cities across Macedonia.

# Installation
Just download the APK file to your smartphone and install it by finding the APK file in your smartphone's File Manager and clicking on it.

# How to use it
When you open the app for the first time you have to register. After that, you login and a list of cities will be shown. When you select a city, a new screen is shown where you select the time and date for your reservation. Next, you select a parking lot in the city that you chose earlier. At the end, there will be your reservation confirmation with a unique QR code and a button you can click to help you navigate to the parking lot using Google Maps. You can access your reservations in the upper-right corner of the app. Enjoy!

# Contents
1. Login/Register Activity
2. Cities Activity
3. Time and Date Activity
4. Parking Lots Activity
5. Reservation Confirmation Activity
6. My Reservations

## Login/Register Activity
In this activity, the user can register and login to this account.

## Cities Activity
RecyclerView showing the cities that are supported by the app. The user can choose in which city he would like to find a parking lot

## Time and Date Activity
The user sets the preferred time and date for his parking needs via a DatePicker, a Spinner and a set time and date button.

## Parking Lots Activity
A RecyclerView of parking lots from the previously selected city. A number of free and reserved spaces is shown for each lot. User picks a parking lot.

## Reservation Confirmation Activity
Here, information about the reservation is shown. Username, the previously picked city, time, date and parking lot can be seen, along with a unique QR code for the reservation. Plus, an extra button for navigation to the reserverd parking lot using Google Maps.

## My Reservations
A RecyclerView listing all the reservations the user has at the moment (maximum 3). By clicking on one reservation (usually after it's no longer needed), the reservation gets deleted.
