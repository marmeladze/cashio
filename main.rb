require 'json'
require './diophanite_equation'

class Logic

  def ba_state
    JSON.parse(File.read('bill_acceptor.json'))
  end

  def cd_state
    JSON.parse(File.read('cash_dispenser.json'))
  end

  def session_amount
    JSON.parse(File.read('session_amount.json'))
  end

  def hold bill
    puts "Holding #{bill}"
    s = session_amount
    s[bill.to_s] +=1
    File.open('session_amount.json', 'w') {|f| f<< s.to_json }
  end

  def reject bill
    puts "Rejecting #{bill}"
    s = session_amount
    s[bill.to_s] -= 1
    File.open('session_amount.json', 'w') {|f| f<< s.to_json }
  end
  
  def stack bill
    puts "Stacked #{bill}"
    s = session_amount
    s[bill.to_s] -= 1
    File.open('session_amount.json', 'w') {|f| f<< s.to_json }

    b = ba_state
    b[bill.to_s] +=1
    File.open('bill_acceptor.json', 'w') {|f| f<< b.to_json }    
  end

  def subset_sum(array, tot)
    (1..array.size).each_with_object([]) { |n,arr|
      array.combination(n).each { |a| arr << a if a.reduce(:+) == tot } }
  end

  def changes_possible_for?(bill)
    cd = cd_state
    splashed_array = cd.keys.map {|k| [k.to_i]*cd[k]}.flatten
    if ((cd[bill.to_s]) && (cd[bill.to_s] > 0)) 
      true
    elsif subset_sum(splashed_array, bill).length > 0
      true
    else
      false
    end
  end

  def able_to_accept?(total, bill)
    if bill > total
      changes_possible_for?(bill - total)
    elsif total == bill
      true
    else
      changes_possible_for?(bill)
    end
  end

  def general_info
    puts "Bill Acceptor"
    ba_state.each {|k,v| puts "#{k} AZN ......... #{v}" }
    puts "Cash Dispenser"
    cd_state.each {|k,v| puts "#{k} AZN ......... #{v}" }
    puts "Session Amount"
    session_amount.each {|k,v| puts "#{k} AZN ......... #{v}" }
  end

  def main_loop
    #TODO: write loop
    puts "CURRENT STATUS"
    general_info
    print "TOTAL AMOUNT SHOULD BE PAID: \t"
    amount = gets.to_i
    print amount
    puts "AVAILABLE INPUTS: 1, 5, 10, 20, 50, 100: \t"
    inserted = gets.chomp.to_i
    hold inserted
    if able_to_accept?(amount, inserted) 
      stack inserted
    else
      reject inserted
    end
  end
end