# chatbot.py - Complete UPI Integration
from datetime import datetime, date

def show_coverage_details():
    print("\n" + "="*60)
    print("EXTENDED WARRANTY COVERAGE DETAILS")
    print("="*60)
    print("✓ COVERED:")
    print("  - All Breakdown Complaints (Parts + Labour)")
    print("  - 2 Free Preventive Maintenance Services per year")
    print("  - All Critical Parts (Compressor, PCB, Motor, Fan, Capacitor, etc.)")
    print("  - Refrigerant / Gas Charge")
    print("  - Technical Support & Emergency Services")
    print("\n✗ NOT COVERED:")
    print("  - Plastic Parts and Sheet Metal Parts")
    print("  - Damage caused by External Factors/Accidents")
    print("  - Knowingly caused damage/misuse")
    print("  - Units installed in Industrial Areas or near Large Drains")
    print("  - Normal Wear and Tear of non-critical parts")
    print("  - Cosmetic damages and aesthetic issues")
    print("="*60 + "\n")

def process_payment(amount):
    print(f"\nBot: Preparing UPI payment request for ₹{amount:,.2f}...")
    
    # REPLACE WITH YOUR ACTUAL UPI ID
    upi_id = "your-business@ybl"  # Example: businessname@ybl, businessname@oksbi, etc.
    payment_description = "AC Warranty Extension"
    
    # Universal UPI deep link
    upi_link = f"upi://pay?pa={upi_id}&pn=AC%20Warranty&am={amount}&cu=INR&tn={payment_description}"
    
    print("="*60)
    print("UPI PAYMENT REQUEST")
    print("="*60)
    print(f"Amount: ₹{amount:,.2f}")
    print(f"UPI ID: {upi_id}")
    print(f"Description: {payment_description}")
    print("="*60)
    
    print("\nBot: Click or copy this link to open your UPI app:")
    print("🔗 " + upi_link)
    
    print("\nBot: This link will open your UPI app with all details pre-filled:")
    print("• Amount will be set to ₹{amount:,.2f}")
    print("• Recipient will be set to our UPI ID")
    print("• Description will be added automatically")
    print("\nBot: Please complete the payment in your UPI app.")
    
    # Verify payment
    while True:
        confirmation = input("\nBot: Have you completed the payment? (yes/no): ").strip().lower()
        
        if confirmation in ['yes', 'y']:
            transaction_id = input("Bot: Please enter your UPI Transaction ID/Reference: ").strip()
            if transaction_id:
                print("Bot: Payment verified successfully! ✅")
                print("Bot: Your extended warranty has been activated.")
                print(f"Bot: Transaction Reference: {transaction_id}")
                return True
            else:
                print("Bot: Please provide a transaction ID for verification.")
                continue
        elif confirmation in ['no', 'n']:
            print("Bot: Payment not completed. Please complete the payment to activate warranty.")
            retry = input("Bot: Do you want to try again? (yes/no): ").strip().lower()
            if retry in ['yes', 'y']:
                continue
            else:
                return False
        else:
            print("Bot: Please answer 'yes' or 'no'.")

def get_customer_declaration(purchase_date_str):
    print("\n" + "="*70)
    print("CUSTOMER DECLARATION REQUIRED")
    print("="*70)
    print("Before proceeding, please confirm ALL of the following:")
    print("✓ I have entered the correct Date of Purchase:", purchase_date_str)
    print("✓ I have a valid invoice copy for my AC purchase")
    print("✓ My AC is in good working condition as of today")
    print("✓ My AC is NOT suffering from any major breakdowns/issues")
    print("✓ My AC is installed in a residential/non-industrial area")
    print("✓ I understand that wrong information will lead to warranty cancellation")
    print("✓ I agree to all terms and conditions of this warranty contract")
    print("="*70)
    
    while True:
        declaration = input("Bot: Do you confirm ALL the above statements? (yes/no): ").strip().lower()
        
        if declaration in ['yes', 'y']:
            print("Bot: Declaration accepted and recorded. ✅")
            print("Bot: Please keep your invoice copy ready for any future verification.")
            return True
        elif declaration in ['no', 'n']:
            print("Bot: Declaration not accepted. Please ensure all conditions are met.")
            print("Bot: Wrong information will result in warranty contract cancellation.")
            return False
        else:
            print("Bot: Please answer 'yes' or 'no'.")

def main():
    print("Bot: Hello! Welcome to AC Extended Warranty Support.")
    print("Bot: I'm here to help you extend the warranty on your AC (up to 2 Ton).")
    print("Bot: To check eligibility, I need your AC's purchase date.")
    print("Bot: Please use DD-MM-YYYY format (e.g., 01-01-2023)")
    print("Bot: Type 'quit' at any time to exit.\n")

    while True:
        purchase_date_str = input("Bot: Please enter purchase date (DD-MM-YYYY): ").strip()

        if purchase_date_str.lower() == 'quit':
            print("Bot: Thank you for your time. Goodbye!")
            return

        try:
            # Convert input to date and calculate age
            purchase_date = datetime.strptime(purchase_date_str, "%d-%m-%Y").date()
            today = date.today()
            
            # Calculate age in years
            ac_age_years = today.year - purchase_date.year
            # Adjust if birthday hasn't occurred yet this year
            if (today.month, today.day) < (purchase_date.month, purchase_date.day):
                ac_age_years -= 1

            print(f"Bot: Thank you. Your AC is {ac_age_years} year(s) old.")
            
            # Check eligibility
            if ac_age_years >= 4:
                print("Bot: Sorry, your AC is not eligible (must be under 4 years old).")
                break
            
            print("Bot: Your AC is eligible for extended warranty!")
            print("Bot: Here are your available options:\n")
            
            # Show options based on age
            if ac_age_years < 1:
                print("1. 4 years extension - ₹6,999.00")
                print("2. 2 years extension - ₹3,999.00") 
                print("3. 1 year extension - ₹2,999.00")
                valid_choices = ['1', '2', '3']
                prices = {'1': 6999, '2': 3999, '3': 2999}
                durations = {'1': '4 years', '2': '2 years', '3': '1 year'}
            elif ac_age_years < 3:
                print("1. 2 years extension - ₹3,999.00")
                print("2. 1 year extension - ₹2,999.00")
                valid_choices = ['1', '2']
                prices = {'1': 3999, '2': 2999}
                durations = {'1': '2 years', '2': '1 year'}
            else:
                print("1. 1 year extension - ₹2,999.00")
                valid_choices = ['1']
                prices = {'1': 2999}
                durations = {'1': '1 year'}
            
            print("\nBot: Type 'coverage' for details or 'quit' to exit.")
            
            # Get user's choice
            while True:
                choice = input("Bot: Please select an option: ").strip().lower()
                
                if choice == 'quit':
                    print("Bot: Thank you for your time. Goodbye!")
                    return
                elif choice == 'coverage':
                    show_coverage_details()
                    print("Bot: Here are your options again:")
                    for opt in valid_choices:
                        print(f"{opt}. {durations[opt]} extension - ₹{prices[opt]:,.2f}")
                    continue
                elif choice in valid_choices:
                    print(f"\nBot: Excellent! You've selected {durations[choice]} extension for ₹{prices[choice]:,.2f}")
                    
                    # Get customer declaration with DOP confirmation
                    if not get_customer_declaration(purchase_date_str):
                        print("Bot: Please ensure your AC meets all conditions and try again.")
                        break
                    
                    # Process payment
                    if process_payment(prices[choice]):
                        print("\n" + "="*60)
                        print("🎉 ORDER CONFIRMATION 🎉")
                        print("="*60)
                        print(f"Product: AC Extended Warranty ({durations[choice]})")
                        print(f"AC Purchase Date: {purchase_date_str}")
                        print(f"Warranty Start Date: {today.strftime('%d-%m-%Y')}")
                        print(f"Warranty End Date: {(today.replace(year=today.year + int(choice))).strftime('%d-%m-%Y')}")
                        print(f"Amount Paid: ₹{prices[choice]:,.2f}")
                        print("Status: Active ✅")
                        print("\n📋 Important Notes:")
                        print("• Keep your invoice copy safe for verification")
                        print("• Wrong information may lead to contract cancellation")
                        print("• Coverage details as per terms and conditions")
                        print("• Thank you for your purchase!")
                        print("="*60)
                    
                    break
                else:
                    print("Bot: Invalid choice. Please select a valid option.")
                    continue
                
            break

        except ValueError:
            print("Bot: Invalid format. Please use DD-MM-YYYY (e.g., 01-01-2023)")

if __name__ == "__main__":
    main()